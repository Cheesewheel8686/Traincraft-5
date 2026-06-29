package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityBoxCartPRR extends AbstractStandardFixedFreightCar
{
	public EntityBoxCartPRR(World world) {
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
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.05F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityBoxCartPRR.class,
						new train.client.render.models.ModelPRRX31Wagon(),
						"PRR_X31a",
						new float[] { 0.0F, -0.38F, 0.0F },
						new float[]{0,180,180},
						null
				)
		);
	}
}