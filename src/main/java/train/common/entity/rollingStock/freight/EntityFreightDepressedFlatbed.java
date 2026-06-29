package train.common.entity.rollingStock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.Modelboulder;
import com.jcirmodelsquad.tcjcir.models.trains.*;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.client.render.models.ModelCharB1;
import train.client.render.models.ModelFT17;
import train.client.render.models.ModelPanzerI;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;

public class EntityFreightDepressedFlatbed extends AbstractStandardFreightCar
{
	public EntityFreightDepressedFlatbed(World world) {
		super(world);
	}

	@Override
	public CargoManager setupCargoManager()
	{
		return new CargoManager(new CargoSpecification[][] {
				{ new CargoSpecification(ModelCharB1.class,
						"trains/CharB1", "Char B1", 0.9, 3.075, -0.0425, -0.45, -0.4,-0.4) },
				{ new CargoSpecification(ModelPanzerI.class,
						"trains/PanzerI", "Panzer I", 0.45, 3.02, -0.11, -0.4, -0.4,-0.4) },
				{ new CargoSpecification(ModelFT17.class,
						"trains/FT17", "FT17", 0.5, 3.0, -0.09, -0.4, -0.4,-0.4) },
				//{ new CargoSpecification(ModelFreedomHeli.class,
				//		"trains/helicopterFreedom", "Freedom Helicoper", 0, 2.625, 0, 0.0, 0.0,0.0) },
				{ new CargoSpecification(ModelBoxcab23Ton.class,
						"trains/boxcab_White", "23 Ton", 0.3, 2.81, -0.1, -0.1, -0.1,-0.1) },
				//{ new CargoSpecification(ModelTGVMobile.class,
				//		"trains/tgvmobile_Orange", "tgv", 0, 2.625, 0, 0.0, 0.0,0.0) },

				//models with the renderrollingstockwithlight shit crash the game when used as cargo loads apparently lol

				{new CargoSpecification(Modelboulder.class,
						"loads/boulder2", "Washaskan Boulder", new CargoSpecification.RenderParameters().setOffset(0.3, 2.75, -0.1)),
				},
		});
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.8F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightDepressedFlatbed.class,
						new train.client.render.models.ModelDepressedFlatbed(),
						"depressed_flatbed_",
						new float[] { -0.1F, 0.15F, -0.075F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}