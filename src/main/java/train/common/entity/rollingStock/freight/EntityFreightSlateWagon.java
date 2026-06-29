package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightSlateWagon extends AbstractStandardFixedFreightCar
{
	public EntityFreightSlateWagon(World world) {
		super(world);
		cargoFilterCategory = CargoItemFilter.ROCK_MATERIAL;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.75F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightSlateWagon.class,
						new train.client.render.models.ModelSlateWagon(),
						"freightslatewagon",
						new float[] { 0.0F, 0.2F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}