package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightOpen2 extends AbstractStandardFixedFreightCar
{
	public EntityFreightOpen2(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.32F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightOpen2.class,
						new train.client.render.models.ModelFreightOpen2(),
						"freightOpen2",
						new float[] { 0.0F, -0.44F, 0.0F },
						new float[] { 0F, 90F, 0F },
						null
				)
		);
	}
}