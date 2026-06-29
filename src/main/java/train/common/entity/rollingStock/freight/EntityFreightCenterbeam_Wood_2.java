package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

import static train.common.enums.CargoItemFilter.WOOD_PRODUCTS;

public class EntityFreightCenterbeam_Wood_2 extends AbstractStandardFixedFreightCar
{
	public EntityFreightCenterbeam_Wood_2(World world) {
		super(world);
		cargoFilterCategory = WOOD_PRODUCTS; // Wood Products
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
						EntityFreightCenterbeam_Wood_2.class,
						new train.client.render.models.ModelFreightCenterBeam_Wood_2(),
						"freight_centerbeam_wood_2_",
						new float[] { 0.0F, -0.44F, 0.0F },
						new float[] { 0F, 90F, 0F },
						null
				)
		);
	}
}