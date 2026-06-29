package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightCenterbeam_Wood_1 extends AbstractStandardFixedFreightCar
{
	public EntityFreightCenterbeam_Wood_1(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS; // Wood Products
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
						EntityFreightCenterbeam_Wood_1.class,
						new train.client.render.models.ModelFreightCenterBeam_Wood_1(),
						"freight_centerbeam_wood_1_",
						new float[] { 0.0F, -0.44F, 0.0F },
						new float[] { 0F, 90F, 0F },
						null
				)
		);
	}
}