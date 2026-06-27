package train.common.entity.rollingStock;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.core.util.ReplacementStreamPlayer;
import train.common.enums.LockoutGroup;
import train.common.library.GuiIDs;

public class EntityJukeBoxCart extends EntityRollingStock {
	
	public boolean isPlaying = false;
	public boolean isInvalid = false;
	public String streamURL = "";
	private Side side;
	public float volume = 1.0f;
	public ReplacementStreamPlayer player;

	public EntityJukeBoxCart(World world)
	{
		super(world);
		dataWatcher.addObject(22, streamURL);
		dataWatcher.addObject(23, 0);
		side = FMLCommonHandler.instance().getEffectiveSide();
		InsertTexture(0, "Jukebox Cart", LockoutGroup.ADMIN);
	}

	@Override
	public void setDead() {
		this.stopStream();
		super.setDead();
		isDead = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && this.updateTicks % 10 == 0) {
			this.dataWatcher.updateObject(22, streamURL);
			if (isPlaying) {
				this.dataWatcher.updateObject(23, 1);
			}
			else {
				this.dataWatcher.updateObject(23, 0);
			}
		}
		if (side == Side.CLIENT) {
			
			if (this.updateTicks % 10 == 0 && !this.isPlaying() && this.dataWatcher.getWatchableObjectInt(23) != 0) {
				this.streamURL = this.dataWatcher.getWatchableObjectString(22);
				this.startStream();
			}
			if ((Minecraft.getMinecraft().thePlayer != null) && (this.player != null) && (!isInvalid)) {
				float vol = (float) getDistanceSq(Minecraft.getMinecraft().thePlayer.posX,
						Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
				if (vol >= (volume * 1000.0F)) {
					this.player.setGain(0);
				} else {
					float v2 = 10000.0F / vol / 100.0F;
//					System.out.println(vol);
					if (v2 > 1.0F) {
						this.player.setGain(volume * Traincraft.proxy.getJukeboxVolume());
					} else {
						float v1 = 1.0f - volume;
						if (v2 - v1 > 0) {
							v2 = v2 - v1;
						} else {
							v2 = 0.0f;
						}
						this.player.setGain(v2 * Traincraft.proxy.getJukeboxVolume());
					}
				}
				if (vol == 0) {
					this.invalidate();
				}
				if (this.isPlaying && this.player.getGainValue() != 0) {
					if (!Minecraft.getMinecraft().thePlayer.getEntityData().hasKey("MusicVolume")) {
						Minecraft.getMinecraft().thePlayer.getEntityData().setFloat("MusicVolume", Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC));
						Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.MUSIC, 0);
					}
				}
				else if (Minecraft.getMinecraft().thePlayer.getEntityData().hasKey("MusicVolume")) {
					Minecraft.getMinecraft().gameSettings.setSoundLevel(SoundCategory.MUSIC, Minecraft.getMinecraft().thePlayer.getEntityData().getFloat("MusicVolume"));
					Minecraft.getMinecraft().thePlayer.getEntityData().removeTag("MusicVolume");
				}
				if (this.isPlaying && rand.nextInt(5) == 0 && (this.player != null && this.player.isPlaying())) {
					int random2 = rand.nextInt(24) + 1;
					worldObj.spawnParticle("note", posX, posY + 1.2D, posZ, random2 / 24.0D, 0.0D, 0.0D);
				}
			}
			
		}
	}

	/**
	 * server side
	 * 
	 * @param url
	 * @param playing
	 */
	public void recievePacket(String url, boolean playing) {
		this.streamURL = url;
		this.isPlaying = playing;
	}

	@SideOnly(Side.CLIENT)
	public void invalidate() {
		isInvalid = true;
		stopStream();
	}

	@SuppressWarnings("static-access")
	public void startStream() {
		
		if (!this.isPlaying) {
			this.isPlaying = true;
			if (side == Side.CLIENT) {
				this.player = new ReplacementStreamPlayer(this.streamURL, this, true);
				player.setGain(0);
				Traincraft.proxy.playerList.add(this.player);
			}
		}
		
	}

	@SuppressWarnings("static-access")
	public void stopStream() {
		
		if (this.isPlaying) {
			this.isPlaying = false;
			if (side == Side.CLIENT && this.player != null) {
				this.player.stopPlayer();
				Traincraft.proxy.playerList.remove(this.player);
			}
		}
		
	}

	public boolean isPlaying() {
		return this.isPlaying;
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
		
		entityplayer.openGui(Traincraft.instance, GuiIDs.JUKEBOX, worldObj, this.getEntityId(), -1, (int) this.posZ);
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
		return 1.85F;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setString("StreamUrl", this.streamURL);
		nbttagcompound.setBoolean("isPlaying", this.isPlaying());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		this.streamURL = nbttagcompound.getString("StreamUrl");
		this.isPlaying = nbttagcompound.getBoolean("isPlaying");
		this.dataWatcher.updateObject(22, streamURL);
		if (isPlaying) {
			this.dataWatcher.updateObject(23, 1);
		} else {
			this.dataWatcher.updateObject(23, 0);
		}
	}

	@Override
	public void onRenderInsertRecord() {
		train.common.Traincraft.traincraftRegistry.RegisterRollingStockModel(
				new train.client.render.register.TrainRenderRecord(
						train.common.library.Info.modID,
						EntityJukeBoxCart.class,
						new train.client.render.models.ModelJukeBox(),
						"jukebox",
						new float[] { 0.0F, -0.42F, 0.0F },
						null,
						null
				)
		);
	}
}
