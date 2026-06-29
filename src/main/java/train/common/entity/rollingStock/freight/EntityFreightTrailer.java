package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightTrailer extends AbstractStandardFixedFreightCar implements IInventory {
	public int freightInventorySize;
	public int numFreightSlots;

	public EntityFreightTrailer(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.6F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightTrailer.class,
						new train.client.render.models.ModelFreightTrailer(),
						"freightTrailer_",
						new float[] { 0.0F, -0.44F, 0.0F },
						new float[] { 0F, 90F, 0F },
						null
				)
		);
	}
}