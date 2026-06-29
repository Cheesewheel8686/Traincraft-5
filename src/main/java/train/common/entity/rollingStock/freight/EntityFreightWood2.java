package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightWood2 extends AbstractStandardFixedFreightCar
{

	public EntityFreightWood2(World world) {
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
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.4F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightWood2.class,
						new train.client.render.models.ModelFreightWood(),
						"flatCartWood2",
						new float[] { 0.0F, -0.44F, 0.0F },
						new float[] { 0F, 90F, 0F },
						null
				)
		);
	}
}