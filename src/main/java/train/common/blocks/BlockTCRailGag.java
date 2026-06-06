package train.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;
import train.common.tile.TileTrainDetector;

import java.util.Random;

public class BlockTCRailGag extends Block {
	private IIcon texture;
	float f = 0.125F;

	public BlockTCRailGag() {
		super(Material.anvil);
		setCreativeTab(Traincraft.tcTab);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(blockX, blockY, blockZ);
		int l = world.getBlockMetadata(blockX, blockY, blockZ);

		if ((te instanceof TileTCRailGag) && player != null) {
			NBTTagCompound entityData = player.getEntityData();
			if (entityData.hasKey("TC_Train_Detector_Pairing")) { // If player is pairing track to detector…
				TileTCRailGag tileTCRailGag = ((TileTCRailGag) te);
				TileEntity possibleTileTCRail = world.getTileEntity(tileTCRailGag.originX, tileTCRailGag.originY, tileTCRailGag.originZ);
				if (possibleTileTCRail instanceof TileTCRail) {
					TileTCRail tileTCRail = ((TileTCRail) possibleTileTCRail);
					int detectorX = entityData.getInteger("TC_Train_Detector_BlockX");
					int detectorY = entityData.getInteger("TC_Train_Detector_BlockY");
					int detectorZ = entityData.getInteger("TC_Train_Detector_BlockZ");
					tileTCRail = tileTCRail.getGreatestParent(world);
					TileEntity tilePossibleDetector = world.getTileEntity(detectorX, detectorY, detectorZ);
					if (tilePossibleDetector instanceof TileTrainDetector) {
						TileTrainDetector trainDetector = (TileTrainDetector) tilePossibleDetector;
						if (!trainDetector.getPairedTrack().contains(tileTCRail)) {
							trainDetector.getPairedTrack().add(tileTCRail);
							tileTCRail.getPairedDetectors().add(trainDetector);
							if (!world.isRemote)
								player.addChatComponentMessage(new ChatComponentText("Added track."));
							entityData.setInteger("TC_Train_Detector_Pairing", entityData.getInteger("TC_Train_Detector_Pairing") + 1);
						} else {
							if (!world.isRemote)
								player.addChatComponentMessage(new ChatComponentText("Track already added."));
						}
					}
				}
			}
			//((TileTCRail)te).printInfo();
		}
		return false;
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return false;
	}

	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};

	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
		TileTCRailGag tileEntity = (TileTCRailGag) world.getTileEntity(i, j, k);
		if (tileEntity != null) {
			world.func_147480_a(tileEntity.originX, tileEntity.originY, tileEntity.originZ, false);
			world.removeTileEntity(tileEntity.originX, tileEntity.originY, tileEntity.originZ);
			// NOTE: func_147480_a = destroyBlock
			for(int x : matrixXZ){
				for(int z : matrixXZ){
					for(int y : matrixY){
						if (world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord)instanceof BlockTCRailGag){
							world.notifyBlockChange((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z  + tileEntity.zCoord), Blocks.air);
							world.markBlockForUpdate((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1 ), (z  + tileEntity.zCoord));
						}
						if (world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord)instanceof BlockTCRail){
							world.notifyBlockChange((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z  + tileEntity.zCoord), Blocks.air);
							world.markBlockForUpdate((x  + tileEntity.xCoord), (y + tileEntity.yCoord + 1 ), (z  + tileEntity.zCoord));
						}
					}
				}
			}

		}
		world.removeTileEntity(i, j, k);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return null;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block par5) {
		TileEntity tileEntity = world.getTileEntity(i, j, k);
		if (tileEntity instanceof TileTCRailGag) {
			if (world.isAirBlock(((TileTCRailGag)tileEntity).originX, ((TileTCRailGag)tileEntity).originY, ((TileTCRailGag)tileEntity).originZ)) {
				// NOTE: func_147480_a = destroyBlock
				world.func_147480_a(i, j, k, false);
				world.removeTileEntity(i, j, k);
			}
			if (!World.doesBlockHaveSolidTopSurface(world, i, j - 1, k) && world.getBlock(i, j-1, k) != BlockIDs.bridgePillar.block) {
				// NOTE: func_147480_a = destroyBlock
				world.func_147480_a(i, j, k, false);
				world.removeTileEntity(i, j, k);
			}
		}
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int i, int j, int k) {
		TileTCRailGag tileEntity = (TileTCRailGag) par1IBlockAccess.getTileEntity(i, j, k);
		if (tileEntity != null) {
			//System.out.println(tileEntity.type+" "+tileEntity.bbHeight);
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, tileEntity.bbHeight, 1.0F);
		}
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileTCRailGag();
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
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

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		TileEntity tileEntity = world.getTileEntity(i, j, k);
		if (tileEntity instanceof TileTCRailGag && !((TileTCRailGag)tileEntity).type.equals("null")) {
			return AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + ((TileTCRailGag)tileEntity).bbHeight, k + 1);
		}
		return null;
	}
}