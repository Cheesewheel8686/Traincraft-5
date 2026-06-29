package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFlatCarRails_DB extends AbstractStandardFixedFreightCar
{
	public EntityFlatCarRails_DB(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.ASSEMBLED_TRAIN_TRACK;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName()
	{
		return "Flat Cart";
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
						EntityFlatCarRails_DB.class,
						new train.client.render.models.ModelFlatCarRails_DB(),
						"flatCarRails_DB_",
						new float[] { 0.0F, -0.44F, 0.0F },
						null,
						null
				)
		);
	}
}