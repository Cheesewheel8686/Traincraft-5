package train.common.entity.rollingStock.passenger.baggagecar;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightHeavyweightBaggage extends AbstractStandardFixedFreightCar
{
	public EntityFreightHeavyweightBaggage(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Heavyweight Baggage";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 5F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightHeavyweightBaggage.class,
						new train.client.render.models.ModelHeavyweightBaggage(),
						"heavyweightBoxcar_",
						new float[] { 0F, 0.1F, -0.05F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}