package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightGrain extends AbstractStandardFixedFreightCar
{
	public EntityFreightGrain(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.GRAIN;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Grain Hopper";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.8F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightGrain.class,
						new train.client.render.models.ModelGrain(),
						"hopper_",
						new float[] { 0.0F, -0.42F, 0.0F },
						null,
						null
				)
		);
	}
}