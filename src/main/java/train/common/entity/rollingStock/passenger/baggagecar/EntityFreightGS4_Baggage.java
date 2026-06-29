package train.common.entity.rollingStock.passenger.baggagecar;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractPassengerCombineCar;

public class EntityFreightGS4_Baggage extends AbstractPassengerCombineCar
{

	public EntityFreightGS4_Baggage(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "GS4 Baggage cart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.1F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightGS4_Baggage.class,
						new train.client.render.models.ModelGS4Baggage(),
						"GS4_baggage_",
						new float[] { 0.0F, 0.025F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.8f,1f,0.8f}
				)
		);
	}
}