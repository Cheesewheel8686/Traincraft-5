package train.common.entity.rollingStock.passenger.baggagecar;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightBaggageMILW extends AbstractStandardFixedFreightCar
{
	public EntityFreightBaggageMILW(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public double getMountedYOffset() {
		return (double) height * 0.0D - 0.30000001192092896D;
	}

	@Override
	public String getInventoryName() {
		return "MILW Baggage";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.675F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightBaggageMILW.class,
						new train.client.render.models.ModelMILWBaggage(),
						"milw_baggage",
						new float[] { 0.1F, 0.1F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,0.9f,0.9f}
				)
		);
	}
}