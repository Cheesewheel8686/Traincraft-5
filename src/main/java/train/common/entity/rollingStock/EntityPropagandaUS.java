package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;

public class EntityPropagandaUS extends EntityRollingStock {


	public EntityPropagandaUS(World world) {
		super(world);
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		//ItemStack var2 = entityplayer.inventory.getCurrentItem();
		playerEntity = entityplayer;
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (locked && !entityplayer.getDisplayName().toLowerCase().equals(this.trainOwner.toLowerCase())) {
			if (!worldObj.isRemote)
				entityplayer.addChatMessage(new ChatComponentText("this train is locked"));
			return true;
		}
		return true;
	}

	@Override
	public boolean isStorageCart() {
		return false;
	}

	@Override
	public boolean isPoweredCart() {
		return false;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.5F;
	}
}
