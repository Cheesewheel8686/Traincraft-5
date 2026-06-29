package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;
import train.common.library.ItemIDs;

import java.util.ArrayList;
import java.util.List;

public class EntityFlatCarLogs_DB extends AbstractStandardFixedFreightCar
{
	public EntityFlatCarLogs_DB(World world) {
		super(world);
		cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Wood transport";
	}

	@Override
	public List<ItemStack> getItemsDropped() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(ItemIDs.minecartFlatCartLogs_DB.item));
		return items;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.84F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFlatCarLogs_DB.class,
						new train.client.render.models.ModelFlatCarLogs_DB(),
						"flatCarLogs_DB_",
						new float[] { 0.0F, -0.44F, 0.0F },
						null,
						null
				)
		);
	}
}