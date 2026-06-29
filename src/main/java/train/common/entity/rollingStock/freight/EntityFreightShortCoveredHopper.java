package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.overlaytexture.EnumOverlayFonts;
import train.common.overlaytexture.OTSpecificationDynamic;

import java.awt.*;

public class EntityFreightShortCoveredHopper extends AbstractStandardFixedFreightCar
{
	public EntityFreightShortCoveredHopper(World world) {
		super(world);
		initOverlayTextures();
		getOverlayTextureContainer().initOverlaySpecification(new OTSpecificationDynamic(
				"Road Name",
				40, 7, null, EnumOverlayFonts.BapSansMid, 7f,  OTSpecificationDynamic.AlignmentMode.ALIGN_LEFT_AND_CLIP,
				new Point[]{ new Point(398, 22), new Point(463, 22) }
		));
		getOverlayTextureContainer().initOverlaySpecification(new OTSpecificationDynamic(
				"Car Number",
				15, 7, null, EnumOverlayFonts.BapSansMid, 7f,  OTSpecificationDynamic.AlignmentMode.ALIGN_RIGHT_AND_CLIP,
				new Point[]{ new Point(425, 22), new Point(489, 22) }
		));
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Short Covered Hopper";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2F;
	}


	@Override
	public void onRenderInsertRecord()
	{
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityFreightShortCoveredHopper.class,
						new train.client.render.models.ModelShortCoveredHopper(),
						"ShortCoveredHopper_",
						new float[] { -2.2F, -0.25F, 0.7F },
						new float[] { 0F, 180F, 180F },
						null
				)
		);
	}
}