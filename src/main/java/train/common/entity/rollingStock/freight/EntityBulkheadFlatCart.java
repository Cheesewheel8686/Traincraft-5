package train.common.entity.rollingStock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.*;
import com.jcirmodelsquad.tcjcir.models.trains.ModelAEM7;
import com.jcirmodelsquad.tcjcir.models.trains.ModelOnion;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.CargoItemFilter;

public class EntityBulkheadFlatCart extends AbstractStandardFreightCar
{
	public EntityBulkheadFlatCart(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS;
	}

	@Override
	public CargoManager setupCargoManager()
	{
		return new CargoManager(new CargoSpecification[][] {
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Oak", "Oak Logs", 0, 2.9, 0)},
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Birch", "Birch Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Spruce", "Spruce Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Jungle", "Jungle Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Redwood", "Redwood Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Fir", "Fir Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelMixedLogs.class, "loads/MixedLogs_Pine", "Pine Logs", 0, 2.9, 0) },
				{ new CargoSpecification(ModelOnion.class, "trains/Onion_Black", "O n i o n", 0, 2.4, 0) },
				{ new CargoSpecification(ModelAEM7.class, "trains/AEM-7_Grey", "highly important cargo", 0, 2.38, 0),
						new CargoSpecification(Modeltoastertiedown.class, "loads/AEM_tiedown", "highly important cargo", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood blank
						"loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood canfor
						"loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood tolky
						"loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood wash1
						"loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood wash2
						"loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//wood foggi
						"loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Thick & Thin
						"loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Oak
						"loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Spruce
						"loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Birch
						"loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Jungle
						"loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Acacia
						"loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Dark Oak
						"loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Pine
						"loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Redwood
						"loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", 0, 2.38, 0) },
				{ new CargoSpecification(Modelwrappedwood_opti_brosbulk.class,//Fir
						"loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", 0, 2.38, 0) },
		});
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Bulkhead Flat Cart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.3F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityBulkheadFlatCart.class,
						new train.client.render.models.ModelBulkheadFlat(),
						"BulkheadFlat_",
						new float[]{-0F, 0.0F, 0F},
						new float[] { 0F, 180F, 180F },
						new float[]{0.9f,1f,0.9f}
				)
		);
	}
}