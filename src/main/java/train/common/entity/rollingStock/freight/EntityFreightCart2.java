package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightCart2 extends AbstractStandardFixedFreightCar
{
	public EntityFreightCart2(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.47F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightCart2.class,
						new train.client.render.models.ModelFreightCart2(),
						"freightcart2_",
						new float[] { 0.0F, -0.40F, 0.0F },
						null,
						null
				)
		);
	}
}