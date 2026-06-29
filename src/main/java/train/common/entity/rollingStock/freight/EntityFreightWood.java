package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightWood extends AbstractStandardFixedFreightCar
{
	public EntityFreightWood(World world) {
		super(world);
		cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Wood transport";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.85F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightWood.class,
						new train.client.render.models.ModelWood(),
						"wood_Full",
						new float[] { 0.0F, -0.42F, 0.0F },
						null,
						null
				)
		);
	}
}