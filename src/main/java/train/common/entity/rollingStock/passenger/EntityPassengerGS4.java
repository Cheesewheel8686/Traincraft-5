package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelGS4Passenger;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassengerGS4 extends EntityRollingStock implements IPassenger {

	public EntityPassengerGS4(World world) {
		super(world);
		InsertTexture(13, "GS4-liner (PH2)");
		InsertTexture(14, "GS4-liner (PH2)");
		InsertTexture(15, "GS4-liner (PH2)");
		InsertTexture(16, "GS4-liner (PH3)");
		InsertTexture(17, "GS4-liner (PH3)");
		InsertTexture(18, "GS4-liner (PH3)");
		InsertTexture(19, "GS4-liner (PH4)");
		InsertTexture(20, "GS4-liner (PH5)");
		InsertTexture(21, "GS4-liner (PH6)");
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
				EntityPassengerGS4.class, new ModelGS4Passenger(),
				"GS4_Passenger_",
				new float[] { 0.0F, 0.03F, 0F },
				new float[] { 0F, 180F, 180F },
				new float[]{0.8f,1f,0.8f}));
	}
}