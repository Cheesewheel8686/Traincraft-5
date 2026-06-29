package tb.dev.rollingstockicon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.utils.devutils.DebugUtil;

import java.util.Random;

public class BlockRollingStockIconGenerator extends BlockContainer {
	static final String DISPLAY_NAME = "Rollingstock Icon Generator Table";

	private IIcon textureTop;
	private IIcon textureBottom;
	private IIcon textureFront;
	private IIcon textureSide;

	public BlockRollingStockIconGenerator() {
		super(Material.wood);
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? textureTop : side == 0 ? textureBottom : side == 3 ? textureFront : textureSide;
	}

	@Override
	public String getLocalizedName() {
		return DISPLAY_NAME;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.isSneaking() || !Boolean.TRUE.equals(DebugUtil.dev)) {
			return false;
		}

		if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileRollingStockIconGenerator) {
			player.openGui(Traincraft.instance, RollingStockIconGeneratorDevBootstrap.GUI_ID, world, x, y, z);
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileRollingStockIconGenerator) {
			TileRollingStockIconGenerator tile = (TileRollingStockIconGenerator) tileEntity;
			Random random = new Random();
			ItemStack stack = tile.getStackInSlot(0);
			if (stack != null) {
				float offsetX = random.nextFloat() * 0.8F + 0.1F;
				float offsetY = random.nextFloat() * 0.8F + 0.1F;
				float offsetZ = random.nextFloat() * 0.8F + 0.1F;
				EntityItem entityItem = new EntityItem(world, x + offsetX, y + offsetY, z + offsetZ, stack);
				world.spawnEntityInWorld(entityItem);
				tile.setInventorySlotContents(0, null);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileRollingStockIconGenerator();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		textureTop = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_table_top");
		textureBottom = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_table_bottom");
		textureFront = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_table_front");
		textureSide = iconRegister.registerIcon(Info.modID.toLowerCase() + ":train_table_side");
	}
}
