/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.LinkedList;

/**
 * @author 02skaplan
 * @author broscolotos
 * <h1>Train Detector Tile Class</h1>
 * <p>Contains all code to run train detector functions on both server and client side.</p><br></br>
 * <h2>Server/Client Differences</h2>
 * <p>Both the client and the server are similarly informed; all fields should be accessible and live on both
 * client and server side</p><br></br>
 * <h2>Tile Functions</h2>
 * <p>The tile stores coordinates of all paired track in a two-dimensional array stored in NBT. However, we cannot update the refernces
 * to the rail tiles as soon as the NBT is loaded if the world has not yet loaded. We use the {@code needsInit} flag, which
 * is run on the first tick of the tile, to update the refernces to the paired track in {@code pairedTrack} as soon as the world
 * is loaded by running {@code updatePairedRails()}.</p><br></br>
 * <h2>What is Done Elsewhere?</h2>
 * <ul>
 * 	<li>Pairing code is run on both client and server and is handled in {@link train.common.blocks.BlockTrainDetector}.</li>
 * 	<li>Entities add and remove themselves from detectors in the {@code handleTrainDetector()} method of {@link train.common.api.EntityRollingStock}.</li>
 * </ul>
 */

public class TileTrainDetector extends TileLockable {

	private ForgeDirection facing;
	private final LinkedList<TileTCRail> pairedTrack;
	// Use EntityMinecart instead of EntityRollingStock so we can store both EntitiesRollingStock and EntityBogies.
	private final LinkedList<EntityMinecart> activeEntities;
	boolean state = false;
	private boolean needsInit = false;
	private int[][] linkedTrackCoordinates;

	public TileTrainDetector() {
		pairedTrack = new LinkedList<>();
		activeEntities = new LinkedList<>();
	}

	public void addEntity(EntityMinecart entity) {
		activeEntities.add(entity);
		setState(true);
	}
	public void removeEntity(EntityMinecart entity) {
		activeEntities.remove(entity);
		if (activeEntities.isEmpty()) {
			setState(false);
		}
	}

	private void setState(boolean state) {
		if (state != this.state) {
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, blockMetadata, 3);
		}
		this.state = state;
	}

	public boolean getState() {
		return !this.activeEntities.isEmpty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		facing = ForgeDirection.getOrientation(nbtTag.getByte("Orientation"));

		// Read paired tracks from NBT.
		NBTTagList tagList = nbtTag.getTagList("PairedTracks", Constants.NBT.TAG_COMPOUND);
		linkedTrackCoordinates = new int[tagList.tagCount()][3];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			int[] coordinateArray = tagCompound.getIntArray("Coordinates");
            System.arraycopy(coordinateArray, 0, linkedTrackCoordinates[i], 0, 3);
		}
		needsInit = true;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);

		if (facing != null) {
			nbtTag.setByte("Orientation", (byte) facing.ordinal());
		}
		else {
			nbtTag.setByte("Orientation", (byte) ForgeDirection.NORTH.ordinal());
		}

		// Write paired tracks to NBT.
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound;
		for (TileTCRail pairedTrack : pairedTrack) {
			tagCompound = new NBTTagCompound();
			tagCompound.setIntArray("Coordinates", new int[]{pairedTrack.xCoord, pairedTrack.yCoord, pairedTrack.zCoord});
			tagList.appendTag(tagCompound);
		}
		nbtTag.setTag("PairedTracks", tagList);
	}

	public ForgeDirection getFacing() {
		return (facing != null ? this.facing : ForgeDirection.NORTH);
	}
	public void setFacing(ForgeDirection face) {
		this.facing = face;
	}
    public LinkedList<TileTCRail> getPairedTrack() {
        return pairedTrack;
    }
	public LinkedList<EntityMinecart> getActiveEntities() {
		return activeEntities;
	}

    @Override
    public void updateEntity() {
		if (needsInit) {
		    needsInit = false;
		    updateLinkedRails();
		}
    }

	/**
	 * <p>Track tiles cannot be added to the paired track list until before the world is fully loaded.</p>
	 * <p>This method is called after the entity is loaded to convert the coordinates stored in NBT
	 * to live references to the track tiles themselves.</p>
	 */
	private void updateLinkedRails() {
		for (int[] coordinateArray : linkedTrackCoordinates) {
			TileEntity te = worldObj.getTileEntity(coordinateArray[0], coordinateArray[1], coordinateArray[2]);
			if (te instanceof TileTCRail) {
				pairedTrack.add(((TileTCRail) te));
				((TileTCRail)te).getPairedDetectors().add(this);
			}
		}
    }
}