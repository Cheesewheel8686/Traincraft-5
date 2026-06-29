package train.common.entity.rollingStock.passenger.rpo;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightHeavyweight extends AbstractStandardFixedFreightCar
{
	public EntityFreightHeavyweight(World world) {
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
		return "Heavyweight Mailcar";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.2F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightHeavyweight.class,
						new train.client.render.models.ModelHeavyweight(),
						"heavyweight_mailcar",
						new float[] { 0.1F, 0.18F, 0F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}