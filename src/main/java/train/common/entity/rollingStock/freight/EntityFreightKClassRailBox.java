package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightKClassRailBox extends AbstractStandardFixedFreightCar
{
	public EntityFreightKClassRailBox(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "K Class Rail Box";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.8F;
	}

	@Override
	public boolean isFictional() {
		return true;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightKClassRailBox.class,
						new train.client.render.models.ModelKClassRailBox(),
						"KClassRailBox_",
						new float[] { -1.5F, 0.1F, 0.125F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}