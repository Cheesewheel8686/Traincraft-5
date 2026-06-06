package train.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.items.ItemWrench;
import train.common.items.TCRailTypes;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.library.track.EnumTracks;
import train.common.tile.TileTCRail;
import train.common.tile.TileTrainDetector;

import java.util.Random;

public class BlockTCRail extends Block {
	private IIcon texture;

	public BlockTCRail() {
		super(Material.anvil);
		setCreativeTab(Traincraft.tcTab);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return false;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
	{
		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(x, y, z);
		if (tileEntity != null && tileEntity.idDrop != null)
		{
			texture = tileEntity.idDrop.getIconFromDamage(0);
			return new ItemStack(tileEntity.idDrop);
		}
		return null;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};

	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(i, j, k);

		// Check if track is paired to any detectors. If so, unpair it before breaking.
		if (tileEntity != null && !tileEntity.getPairedDetectors().isEmpty()) {
			for (TileTrainDetector trainDetector : tileEntity.getPairedDetectors())
				trainDetector.getPairedTrack().remove(tileEntity);
			tileEntity.getPairedDetectors().clear();
		}

		if (tileEntity != null && tileEntity.isLinkedToRail) {
			// NOTE: func_147480_a = destroyBlock
			world.func_147480_a(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ, false);
			world.removeTileEntity(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ);
		}

		if (tileEntity != null && (tileEntity.idDrop != null) && !world.isRemote)
		{
			this.dropBlockAsItem(world, i, j, k, new ItemStack(tileEntity.idDrop, 1, 0));
		}

		for(int x : matrixXZ){
			for(int z : matrixXZ){
				for(int y : matrixY){
					if (tileEntity != null && world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord)instanceof BlockTCRailGag){
						world.notifyBlockChange((x +  tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z  + tileEntity.zCoord), Blocks.air);
						world.markBlockForUpdate((x + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z + tileEntity.zCoord));
					}
					if (tileEntity != null && world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord)instanceof BlockTCRail){
						world.notifyBlockChange((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z  + tileEntity.zCoord), Blocks.air);
						world.markBlockForUpdate((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1 ), (z  + tileEntity.zCoord));
					}
				}
			}
		}

		world.removeTileEntity(i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block par5) {
		TileEntity tile = world.getTileEntity(i, j, k);
		if (tile == null || !(tile instanceof TileTCRail))
			return;

		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(i, j, k);
		if (tileEntity != null && tileEntity.isLinkedToRail) {
			if (world.isAirBlock(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ)) {
				// NOTE: func_147480_a = destroyBlock
				world.removeTileEntity(i, j, k);
				world.func_147480_a(i, j, k, false);
			}
		}
		if (!World.doesBlockHaveSolidTopSurface(world, i, j - 1, k) && world.getBlock(i, j-1, k) != BlockIDs.bridgePillar.block) {
			// NOTE: func_147480_a = destroyBlock
			world.func_147480_a(i, j, k, false);
			world.removeTileEntity(i, j, k);
		}
		if (tileEntity != null && !world.isRemote)
		{
			boolean flag = world.isBlockIndirectlyGettingPowered(i, j, k);
			if (tileEntity.getSwitchState() != flag) {
				tileEntity.changeSwitchState(world, tileEntity, i, j, k);
			}
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileTCRail();
	}

	@Override
	public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(blockX, blockY, blockZ);
		int l = world.getBlockMetadata(blockX, blockY, blockZ);

		if ((te instanceof TileTCRail) && player != null) {
			NBTTagCompound entityData = player.getEntityData();
			if (!entityData.hasKey("TC_Train_Detector_Pairing")) {
				if (player.inventory != null && player.inventory.getCurrentItem() != null
						&& (player.inventory.getCurrentItem().getItem() instanceof ItemWrench)
						&& ((TileTCRail) te).getType() != null
						&& ((TileTCRail) te).getType().equals(EnumTracks.SMALL_STRAIGHT.getLabel())) {
					// If player is rotating the track…
					l++;
					if (l > 3)
						l = 0;
					world.setBlockMetadataWithNotify(blockX, blockY, blockZ, l, 2);
					((TileTCRail) te).hasRotated = true;
					return true;
				}
			} else { // If player is pairing track to detector…
				TileTCRail tileTCRail = ((TileTCRail) te);
				int detectorX = entityData.getInteger("TC_Train_Detector_BlockX");
				int detectorY = entityData.getInteger("TC_Train_Detector_BlockY");
				int detectorZ = entityData.getInteger("TC_Train_Detector_BlockZ");
				TileEntity tilePossibleDetector = world.getTileEntity(detectorX, detectorY, detectorZ);
				tileTCRail = tileTCRail.getGreatestParent(world);
				if (tilePossibleDetector instanceof TileTrainDetector) {
					TileTrainDetector trainDetector = (TileTrainDetector) tilePossibleDetector;
					if (!trainDetector.getPairedTrack().contains(tileTCRail)) {
						trainDetector.getPairedTrack().add(tileTCRail);
						tileTCRail.getPairedDetectors().add(trainDetector);
						tileTCRail.markDirty();
						if (!world.isRemote)
							player.addChatComponentMessage(new ChatComponentText("Added track."));
						entityData.setInteger("TC_Train_Detector_Pairing", entityData.getInteger("TC_Train_Detector_Pairing") + 1);
					} else {
						if (!world.isRemote)
							player.addChatComponentMessage(new ChatComponentText("Track already added."));
					}
				}
			}

			if (TCRailTypes.isSlopeTrack((TileTCRail) te) || TCRailTypes.isCurvedSlopeTrack((TileTCRail) te))
			{
				if (player.getUniqueID().toString().equals(((TileTCRail) te).getOwnerUUID()) && player != null
						&& player.inventory != null
						&& player.inventory.getCurrentItem() != null
						&& player.inventory.getCurrentItem().getItem() instanceof ItemBlock)
				{
					Block block = Block.getBlockFromItem(player.inventory.getCurrentItem().getItem());
					int blockID = Block.getIdFromBlock(block);
					((TileTCRail) te).setBallastMaterial(blockID);
					((TileTCRail) te).ballastMetadata = player.inventory.getCurrentItem().getItemDamage();
				}
			}

			//((TileTCRail)te).printInfo();
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":tracks/rail_normal_turned");
	}

	@Override
	public IIcon getIcon(int i, int j) {
		return texture;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return world==null||world.isRemote?
				AxisAlignedBB.getBoundingBox(i -18f, j, k -18f, i +18f, j, k +18f)
		:
				AxisAlignedBB.getBoundingBox(i + this.minX, j + this.minY, k + this.minZ, i + this.maxX, j, k + this.maxZ);
	}
}
