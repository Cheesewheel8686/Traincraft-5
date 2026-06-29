package tb.dev.rollingstockicon;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.utils.devutils.DebugUtil;

/**
 * Dev-source registration for the icon generator table.
 * This class intentionally stays under src/dev/java so production jars do not contain the block,
 * tile entity, GUI, container, or file-writing helpers.
 */
public class RollingStockIconGeneratorDevBootstrap {
	public static final int GUI_ID = 9001;
	public static Block block;

	private RollingStockIconGeneratorDevBootstrap() {
	}

	public static void init() {
		if (!Boolean.TRUE.equals(DebugUtil.dev) || block != null) {
			return;
		}

		block = new BlockRollingStockIconGenerator()
				.setHardness(1.7F)
				.setStepSound(Block.soundTypeWood)
				.setBlockName(Info.modID + ":rollingStockIconGenerator");
		GameRegistry.registerBlock(block, ItemBlockRollingStockIconGenerator.class, "rollingStockIconGenerator");
		GameRegistry.registerTileEntity(TileRollingStockIconGenerator.class, "TileRollingStockIconGenerator");
		Traincraft.proxy.registerDevGuiHandler(new DevGuiHandler());
		Traincraft.tcLog.info("Registered dev-only rollingstock icon generator.");
	}

	private static class DevGuiHandler implements IGuiHandler {
		@Override
		public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			if (id != GUI_ID || !Boolean.TRUE.equals(DebugUtil.dev)) {
				return null;
			}
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			return tileEntity instanceof TileRollingStockIconGenerator
					? new ContainerRollingStockIconGenerator(player.inventory, (TileRollingStockIconGenerator) tileEntity)
					: null;
		}

		@Override
		public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			if (id != GUI_ID || !Boolean.TRUE.equals(DebugUtil.dev)) {
				return null;
			}
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			return tileEntity instanceof TileRollingStockIconGenerator
					? new GuiRollingStockIconGenerator(player.inventory, (TileRollingStockIconGenerator) tileEntity)
					: null;
		}
	}
}
