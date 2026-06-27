package train.common.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.core.handlers.ConfigHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TrainSaveLifecycleLogger {
	private static final String LOG_FILE_PREFIX = "train-save-lifecycle-";
	private static final String LOG_FILE_SUFFIX = ".log";

	private TrainSaveLifecycleLogger() {}

	public static void log(String event, Entity entity, String detail) {
		log(event, entity, null, detail);
	}

	public static void log(String event, Entity entity, String entityString, String detail) {
		if (!ConfigHandler.ENABLE_TRAIN_SAVE_LIFECYCLE_LOGGING || entity == null || entity.worldObj == null || entity.worldObj.isRemote) {
			return;
		}

		String line = buildLine(event, entity, entityString, detail);
		writeLine(line);
	}

	public static void logSavedEntityNbt(String event, Entity entity, String entityString, NBTTagCompound tag, String detail) {
		if (!ConfigHandler.ENABLE_TRAIN_SAVE_LIFECYCLE_LOGGING || entity == null || entity.worldObj == null || entity.worldObj.isRemote) {
			return;
		}

		String sizeDetail = detail;
		if (tag != null) {
			sizeDetail = appendDetail(sizeDetail, "nbtBytes=" + measureUncompressedBytes(tag));
			sizeDetail = appendDetail(sizeDetail, "nbtCompressedBytes=" + measureCompressedBytes(tag));
			sizeDetail = appendDetail(sizeDetail, "nbtKeys=" + tag.func_150296_c().size());
		}

		writeLine(buildLine(event, entity, entityString, sizeDetail));
	}

	public static void logAttackRemovalAllowed(Entity entity, DamageSource damageSource, float damage, String context) {
		logAttackRemovalDecision("ATTACK_DESTROY_ALLOWED", entity, damageSource, damage, context, "");
	}

	public static void logAttackRemovalAllowedGuardDisabled(Entity entity, DamageSource damageSource, float damage, String context) {
		logAttackRemovalDecision("ATTACK_DESTROY_ALLOWED", entity, damageSource, damage, context, "guardDisabled=true");
	}

	public static void logAttackRemovalDeniedNoPlayer(Entity entity, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_NO_PLAYER", entity, damageSource, damage, context, "");
	}

	public static void logAttackRemovalDeniedFakePlayer(Entity entity, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_FAKE_PLAYER", entity, damageSource, damage, context, "");
	}

	public static void logAttackRemovalDeniedRemoteWorld(Entity entity, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_REMOTE_SOURCE", entity, damageSource, damage, context, "trainWorldRemote=" + (entity.worldObj != null && entity.worldObj.isRemote));
	}

	public static void logAttackRemovalDeniedCrossWorld(Entity entity, EntityPlayerMP player, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_REMOTE_SOURCE", entity, damageSource, damage, context, "playerDim=" + player.dimension + " trainDim=" + entity.dimension);
	}

	public static void logAttackRemovalDeniedIndirectSource(Entity entity, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_REMOTE_SOURCE", entity, damageSource, damage, context, "sourceIsDirectPlayer=false");
	}

	public static void logAttackRemovalDeniedDamageType(Entity entity, DamageSource damageSource, float damage, String context) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_DAMAGE_TYPE", entity, damageSource, damage, context, "");
	}

	public static void logAttackRemovalDeniedDistance(Entity entity, DamageSource damageSource, float damage, String context, double distanceSq, double maxDistance) {
		logDeniedAttackRemoval("ATTACK_DESTROY_DENIED_DISTANCE", entity, damageSource, damage, context, "distanceSq=" + distanceSq + " maxDistance=" + maxDistance);
	}

	private static void logAttackRemovalDecision(String event, Entity entity, DamageSource damageSource, float damage, String context, String extraDetail) {
		log(event, entity, buildDamageSourceDetail(damageSource, damage, context, extraDetail));
	}

	private static void logDeniedAttackRemoval(String event, Entity entity, DamageSource damageSource, float damage, String context, String extraDetail) {
		if (ConfigHandler.LOG_DENIED_TRAIN_REMOVAL) {
			logAttackRemovalDecision(event, entity, damageSource, damage, context, extraDetail);
		}
	}

	private static String buildDamageSourceDetail(DamageSource damageSource, float damage, String context, String extraDetail) {
		StringBuilder detail = new StringBuilder();
		detail.append("context=").append(context);
		detail.append(" incomingDamage=").append(damage);
		if (damageSource == null) {
			detail.append(" damageSource=null");
		} else {
			detail.append(" damageType=").append(damageSource.getDamageType());
			detail.append(" projectile=").append(damageSource.isProjectile());
			detail.append(" explosion=").append(damageSource.isExplosion());
			detail.append(" fire=").append(damageSource.isFireDamage());
			detail.append(" magic=").append(damageSource.isMagicDamage());
			appendEntityDamageDetail(detail, "attacker", damageSource.getEntity());
			appendEntityDamageDetail(detail, "source", damageSource.getSourceOfDamage());
		}
		if (extraDetail != null && extraDetail.length() > 0) {
			detail.append(' ').append(extraDetail);
		}
		return detail.toString();
	}

	private static void appendEntityDamageDetail(StringBuilder detail, String label, Entity entity) {
		if (entity == null) {
			detail.append(' ').append(label).append("=null");
			return;
		}
		detail.append(' ').append(label).append("Class=").append(entity.getClass().getName());
		detail.append(' ').append(label).append("Id=").append(entity.getEntityId());
		detail.append(' ').append(label).append("Uuid=").append(entity.getUniqueID());
		detail.append(' ').append(label).append("Dim=").append(entity.dimension);
		detail.append(' ').append(label).append("Pos=(").append(entity.posX).append(',').append(entity.posY).append(',').append(entity.posZ).append(')');
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			detail.append(' ').append(label).append("Name=").append(player.getDisplayName());
			ItemStack held = player.inventory != null ? player.inventory.getCurrentItem() : null;
			detail.append(' ').append(label).append("Held=").append(held != null && held.getItem() != null ? held.getItem().delegate.name() : "null");
		}
	}

	private static void writeLine(String line) {
		File directory = new File(new File(Traincraft.configDirectory, "traincraft"), "save-lifecycle");
		File logFile = new File(directory, getDailyLogFileName());

		try {
			if (!directory.exists() && !directory.mkdirs()) {
				Traincraft.tcLog.warn("Unable to create Traincraft save lifecycle log directory: " + directory.getAbsolutePath());
				return;
			}
			FileWriter writer = new FileWriter(logFile, true);
			try {
				writer.write(line);
				writer.write(System.getProperty("line.separator"));
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			Traincraft.tcLog.warn("Unable to write Traincraft save lifecycle log: " + line, e);
		}
	}

	private static String getDailyLogFileName() {
		return LOG_FILE_PREFIX + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + LOG_FILE_SUFFIX;
	}

	private static int measureUncompressedBytes(NBTTagCompound tag) {
		try {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(bytes);
			try {
				CompressedStreamTools.write(tag, output);
			} finally {
				output.close();
			}
			return bytes.size();
		} catch (IOException e) {
			return -1;
		}
	}

	private static int measureCompressedBytes(NBTTagCompound tag) {
		try {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			CompressedStreamTools.writeCompressed(tag, bytes);
			return bytes.size();
		} catch (IOException e) {
			return -1;
		}
	}

	private static String appendDetail(String detail, String addition) {
		if (detail == null || detail.length() == 0) {
			return addition;
		}
		return detail + " " + addition;
	}

	private static String buildLine(String event, Entity entity, String entityString, String detail) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis());
		sb.append(" event=").append(event);
		sb.append(" class=").append(entity.getClass().getName());
		sb.append(" entityId=").append(entity.getEntityId());
		if (entityString != null) {
			sb.append(" entityString=").append(entityString);
		}
		sb.append(" uuid=").append(entity.getUniqueID());
		sb.append(" dim=").append(entity.dimension);
		sb.append(" pos=(").append(entity.posX).append(',').append(entity.posY).append(',').append(entity.posZ).append(')');
		sb.append(" chunk=(").append(entity.chunkCoordX).append(',').append(entity.chunkCoordZ).append(')');
		sb.append(" motion=(").append(entity.motionX).append(',').append(entity.motionY).append(',').append(entity.motionZ).append(')');
		sb.append(" isDead=").append(entity.isDead);
		sb.append(" addedToChunk=").append(entity.addedToChunk);
		if (entity instanceof AbstractTrains) {
			AbstractTrains train = (AbstractTrains)entity;
			sb.append(" trainId=").append(train.uniqueID);
			sb.append(" link1=").append(train.Link1);
			sb.append(" link2=").append(train.Link2);
			sb.append(" trainType=").append(train.trainType);
		}
		if (detail != null && detail.length() > 0) {
			sb.append(" detail=\"").append(detail.replace('"', '\'')).append('"');
		}
		return sb.toString();
	}
}
