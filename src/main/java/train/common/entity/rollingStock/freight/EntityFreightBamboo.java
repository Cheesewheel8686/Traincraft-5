package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightBamboo extends AbstractStandardFixedFreightCar
{
	public EntityFreightBamboo(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.55F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightBamboo.class,
						new train.client.render.models.ModelBambooTrainCargo(),
						"bamboo_freight_",
						new float[] { 0.1F, 0F, 0F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}