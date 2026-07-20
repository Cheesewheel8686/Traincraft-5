package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelGS4Tavern;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassengerGS4_Observatory extends AbstractPassengerCar {

	public EntityPassengerGS4_Observatory(World world) {
		super(world);
		InsertTexture(11, "GS4-liner (PH2)");
		InsertTexture(12, "GS4-liner (PH3)");
		InsertTexture(13, "GS4-liner (PH4)");
	}

		@Override
	public void updateRiderPosition() {
		if(riddenByEntity!=null) {
			riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.17, posZ);
		}
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.1F;
	}


	@Override
	public String transportCountry()
	{
		return "US";
	}
	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerGS4_Observatory.class, new ModelGS4Tavern(),
				"GS4_Tavern_",
				new float[] { 0.0F, 0.025F, 0F },
				new float[] { 0F, 180F, 180F },
				new float[] {0.8f,1f,0.8f}));
	}
}