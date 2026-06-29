package train.common.entity.rollingStock.workcart;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractWorkCart;

public class EntityMailWagen_DB extends AbstractWorkCart
{

	public EntityMailWagen_DB(World world) {
		super(world);
	}

	

	@Override
	public double getAdditionalYOffset()
	{
		return 0.15F;
	}

	@Override
	public String getInventoryName() {
		return "Mail Wagen";
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
						EntityMailWagen_DB.class,
						new train.client.render.models.ModelMailWagen(),
						"mailWagen_DB",
						new float[] { 0.0F, -0.44F, 0.0F },
						null,
						null
				)
		);
	}
}