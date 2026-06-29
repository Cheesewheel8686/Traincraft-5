package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightGondola_DB extends AbstractStandardFixedFreightCar
{
	public EntityFreightGondola_DB(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.84F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightGondola_DB.class,
						new train.client.render.models.ModelFreightGondola_DB(),
						"freightGondola_DB_",
						new float[] { 0.0F, -0.44F, 0.0F },
						null,
						null
				)
		);
	}
}