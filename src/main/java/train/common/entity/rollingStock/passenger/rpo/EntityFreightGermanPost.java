package train.common.entity.rollingStock.passenger.rpo;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightGermanPost extends AbstractStandardFixedFreightCar
{
	public EntityFreightGermanPost(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.955F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightGermanPost.class,
						new train.client.render.models.ModelGermanPost(),
						"german_post_",
						new float[] { -1F, 0.15F, -0.075F },
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}