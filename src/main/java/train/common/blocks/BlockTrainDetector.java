package train.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;
import train.common.entity.TrustedPlayer;
import train.common.items.ItemPadlock;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.library.ItemIDs;
import train.common.tile.TileHelper;
import train.common.tile.TileTCRail;
import train.common.tile.TileTrainDetector;

/**
 * @author 02skaplan
 * @author broscolotos
 * <h1>Train Detector Block Class</h1>
 * <p>Manages the Train Detector block. Primarily handles user interaction (pairing/resetting using composite wrench and
 * right-clicking to access padlock menu) and manages its associated {@link train.common.tile.TileTrainDetector}.
 * Most other code pertaining to the Train Detector is found in {@link train.common.blocks.BlockTrainDetector} and
 * the {@code handleTrainDetector()} method of {@link train.common.api.EntityRollingStock}.</p><br></br>
 * <h2>Server/Client Differences</h2>
 * <p>Both the client and the server are similarly informed. Pairing is done on both client and server side to avoid
 * the need for a packet. The only syncing occurs when NBT is loaded and that utilizes the
 * default tile entity packet, {@link net.minecraft.network.play.server.S35PacketUpdateTileEntity}.</p><br></br>
 */
public class BlockTrainDetector extends BlockContainer {

	private IIcon textureTop;
	private IIcon textureBottom;
	private IIcon textureFront;
	private IIcon textureSide;

	public BlockTrainDetector(int j) {
		super(Material.wood);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
	}

	@Override
	public IIcon getIcon(int i, int j) {
		if (i == 1) {
			return textureTop;
		}
		if (i == 0) {
			return textureBottom;
		}
		if (i == 3) {
			return textureFront;
		}
		else {
			return textureSide;
		}
	}

	@Override
	public IIcon getIcon(IBlockAccess worldAccess, int i, int j, int k, int side) {
		if (((TileTrainDetector) worldAccess.getTileEntity(i, j, k)).getFacing() != null) {
			side = TileHelper.getOrientationFromSide(((TileTrainDetector) worldAccess.getTileEntity(i, j, k)).getFacing(), ForgeDirection.getOrientation(side)).ordinal();
		}
		return side == 1 ? textureTop : side == 0 ? textureBottom : side == 3 ? textureFront : textureSide;
	}

	@Override
	public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(blockX, blockY, blockZ);
		if (te instanceof TileTrainDetector) {
			TileTrainDetector detectorTile = ((TileTrainDetector) te);
			ItemStack handItem = player.getHeldItem();
			if (handItem == null || (handItem.getItem() != ItemIDs.composite_wrench.item && handItem.getItem() != ItemIDs.padlock.item))
				return false; //don't know if this is the correct return
			if (handItem.getItem() == ItemIDs.padlock.item && player.isSneaking()) {
				// If player is trying to access the lock menu using the padlock…
				if (world.isRemote && (player.isSneaking()) && (player.inventory.getCurrentItem() != null) && (player.inventory.getCurrentItem().getItem() instanceof ItemPadlock)
						&& ((player.getDisplayName().equalsIgnoreCase(detectorTile.getOwner())) || (player.canCommandSenderUseCommand(2, "")))) {
					player.openGui(Traincraft.instance, GuiIDs.LOCK_MENU_LOCKABLES, world, detectorTile.xCoord, detectorTile.yCoord, detectorTile.zCoord);
				} else {
					return false;
				}
			} else if (!detectorTile.isLocked() || (TrustedPlayer.isPlayerTrusted(player.getDisplayName(), detectorTile.getTrustedList()) || detectorTile.getOwner().equalsIgnoreCase(player.getDisplayName()))) {
				// If player is trusted to modify the detector…
				if (handItem.getItem() == ItemIDs.composite_wrench.item && player.isSneaking()) {
					// Clearing paired tracks.
					for (TileTCRail linkedRail : detectorTile.getPairedTrack()) {
						linkedRail.getPairedDetectors().remove(detectorTile);
					}
					detectorTile.getPairedTrack().clear();
					// All entities that have stored this detector will refresh after the next tick.
					// Entities stored in the detector will likewise be cleared after the next tick.
					if (!world.isRemote)
						player.addChatComponentMessage(new ChatComponentText("Cleared all paired tracks from detector."));
				} else if (handItem.getItem() == ItemIDs.composite_wrench.item) {
					// Start or end pairing.
					NBTTagCompound playerMetadata = player.getEntityData();
					if (!playerMetadata.hasKey("TC_Train_Detector_Pairing")) {
						// Start Pairing
						if (!world.isRemote)
							player.addChatComponentMessage(new ChatComponentText("Starting track pairing."));
						playerMetadata.setInteger("TC_Train_Detector_Pairing", 0);
						playerMetadata.setInteger("TC_Train_Detector_BlockX", blockX);
						playerMetadata.setInteger("TC_Train_Detector_BlockY", blockY);
						playerMetadata.setInteger("TC_Train_Detector_BlockZ", blockZ);
					} else {
						// End Pairing
						int numPairedTracks = playerMetadata.getInteger("TC_Train_Detector_Pairing");
						if (!world.isRemote)
							player.addChatComponentMessage(new ChatComponentText("Ending track pairing. A total of " + numPairedTracks + " have been paired."));
						playerMetadata.removeTag("TC_Train_Detector_Pairing");
						playerMetadata.removeTag("TC_Train_Detector_BlockX");
						playerMetadata.removeTag("TC_Train_Detector_BlockY");
						playerMetadata.removeTag("TC_Train_Detector_BlockZ");
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
		TileTrainDetector tileDetector = (TileTrainDetector) world.getTileEntity(i, j, k);
		if (tileDetector != null) {
			for (TileTCRail linkedRail : tileDetector.getPairedTrack()) {
			    linkedRail.getPairedDetectors().remove(tileDetector);
			}
			tileDetector.getPairedTrack().clear();
		}
		super.breakBlock(world, i, j, k, par5, par6);
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		world.markBlockForUpdate(i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
		TileTrainDetector detector = (TileTrainDetector) world.getTileEntity(i, j, k);
		if (detector != null) {
			detector.setOwner(entityliving.getCommandSenderName());
			world.markBlockForUpdate(i, j, k);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTrainDetector();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		textureTop = iconRegister.registerIcon(Info.modID.toLowerCase() + ":detector_bottom");
		textureBottom = iconRegister.registerIcon(Info.modID.toLowerCase() + ":detector_bottom");
		textureFront = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_detector_side");
		textureSide = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_detector_side");
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileTrainDetector) {
			TileTrainDetector detectorTile = (TileTrainDetector) tile;
			if (detectorTile.getState())
				return 15;
		}
		return 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		return isProvidingWeakPower(world, x, y, z,  side);
	}

	@Override
	public boolean canProvidePower() {
	    return true;
	}
}