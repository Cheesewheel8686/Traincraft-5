package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightCartSmall extends AbstractStandardFixedFreightCar implements IInventory {
	public EntityFreightCartSmall(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	
	
	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.45F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightCartSmall.class,
						new train.client.render.models.ModelSmallFreightCart(),
						"freightCartSmall",
						new float[] { 0.0F, -0.20F, 0.0F },
						null,
						null
				)
		);
	}
}