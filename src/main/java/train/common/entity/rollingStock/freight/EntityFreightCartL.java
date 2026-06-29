package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightCartL extends AbstractStandardFixedFreightCar
{
	public EntityFreightCartL(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.75F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightCartL.class,
						new train.client.render.models.ModelFreightCarL(),
						"freightCarL_",
						new float[] { 0F, 0.2F, 0.825F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}