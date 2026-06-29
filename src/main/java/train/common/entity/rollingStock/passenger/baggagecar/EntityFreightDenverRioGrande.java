package train.common.entity.rollingStock.passenger.baggagecar;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightDenverRioGrande extends AbstractStandardFixedFreightCar
{
	public EntityFreightDenverRioGrande(World world)
	{
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
		return "DRG Baggage";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.15F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightDenverRioGrande.class,
						new train.client.render.models.ModelDRGBaggage(),
						"drg_baggage_",
						new float[] { 0.0F, 0.14F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}