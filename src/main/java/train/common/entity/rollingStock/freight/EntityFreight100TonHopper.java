package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreight100TonHopper extends AbstractStandardFixedFreightCar {
	public EntityFreight100TonHopper(World world) {
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
		return 2.9F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreight100TonHopper.class,
						new train.client.render.models.Model100TonHopper(),
						"freight_100tonhopper_",
						new float[] { -0.1F, 0.0F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}