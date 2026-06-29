package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightHopperUS extends AbstractStandardFixedFreightCar
{
	public EntityFreightHopperUS(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Freight Hopper";
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
						EntityFreightHopperUS.class,
						new train.client.render.models.ModelFreightHopper(),
						"freightHopperUS_",
						new float[] { 0.0F, -0.47F, 0.0F },
						null,
						null
				)
		);
	}
}