package train.common.utils;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.core.handlers.ConfigHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class TrainSaveLifecycleLogger {
	private static final String LOG_FILE_NAME = "train-save-lifecycle.log";

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

	private static void writeLine(String line) {
		File directory = new File(new File(Traincraft.configDirectory, "traincraft"), "save-lifecycle");
		File logFile = new File(directory, LOG_FILE_NAME);

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
