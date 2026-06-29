package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightWagenDB extends AbstractStandardFixedFreightCar
{
	public EntityFreightWagenDB(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Freight Wagen (DB)";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.8F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightWagenDB.class,
						new train.client.render.models.ModelFreightWagenDB(),
						"freightWagen_DB_",
						new float[] { 0.0F, -0.44F, 0.0F },
						null,
						null
				)
		);
	}
}