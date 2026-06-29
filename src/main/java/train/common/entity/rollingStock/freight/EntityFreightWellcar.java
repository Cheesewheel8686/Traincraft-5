package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightWellcar extends AbstractStandardFixedFreightCar implements IInventory {

	public EntityFreightWellcar(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Freight Wellcar";
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
						EntityFreightWellcar.class,
						new train.client.render.models.ModelWellcar(),
						"wellcar_",
						new float[] { 0.0F, 0.0F, 0.0F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}
