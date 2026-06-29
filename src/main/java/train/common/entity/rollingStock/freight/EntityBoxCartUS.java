package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityBoxCartUS extends AbstractStandardFixedFreightCar
{

	public EntityBoxCartUS(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{
		InsertTexture(16, "FNCC (Oldstyle)");
		InsertTexture(17, "FNCC");
		InsertTexture(18, "RBOX");
	}

	@Override
	public double getMountedYOffset() {
		return (double) height * 0.0D - 0.30000001192092896D;
	}


	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.65F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityBoxCartUS.class,
						new train.client.render.models.ModelBoxCartUS(),
						"boxCartUS_",
						new float[] { 0.0F, -0.45F, 0.0F },
						null,
						null
				)
		);
	}
}