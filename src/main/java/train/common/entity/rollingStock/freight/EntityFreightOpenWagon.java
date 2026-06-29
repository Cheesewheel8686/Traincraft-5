package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightOpenWagon extends AbstractStandardFixedFreightCar
{
	public EntityFreightOpenWagon(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.7F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightOpenWagon.class,
						new train.client.render.models.ModelOpenWagon(),
						"openwagon_",
						new float[] { 0.0F, -0.47F, 0.0F },
						null,
						null
				)
		);
	}
}