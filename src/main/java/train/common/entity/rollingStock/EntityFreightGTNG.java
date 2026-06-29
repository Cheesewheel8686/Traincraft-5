package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightGTNG extends AbstractStandardFixedFreightCar {

	public EntityFreightGTNG(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Minecart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.025F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightGTNG.class,
						new train.client.render.ModelGTNG(),
						"GTNGOreWagon",
						new float[] { 0.0F, 0.2F, 0.0F },
						new float[]{0,0,180},
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}