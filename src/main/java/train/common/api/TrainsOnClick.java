package train.common.api;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import train.common.core.network.PacketInterchangeReportGui;
import train.common.items.ItemInterchangeTransferReportBoard;
import train.common.items.ItemPaintbrushThing;
import train.common.library.GuiIDs;
import train.common.utils.InterchangeTransferReportGenerator;
import train.common.Traincraft;
import train.common.core.network.PacketParkingBrake;
import train.common.library.ItemIDs;
import train.common.utils.interchangetransferreport.InterchangeTransferReportGenerator.InterchangeReportDraft;

import java.util.HashMap;

public class TrainsOnClick
{
	private static final long INTERCHANGE_REPORT_COOLDOWN_MS = 3000L;
	private static final int INTERCHANGE_REPORT_MAX_ROWS = 256;

	public boolean onClickWithStake(AbstractTrains train, ItemStack itemstack, EntityPlayer playerEntity, World world) {
		if (itemstack != null && itemstack.getItem() == ItemIDs.stake.item && !world.isRemote &&
				(FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer() || !train.isLinked() || train.getTransportOwner().equals(playerEntity.getDisplayName()) || train.getTransportOwner().equals("") || train.getTransportOwner()==null)) {

			if (playerEntity.isSneaking() && train instanceof Locomotive) {
				if (!train.canBeAdjusted(train)) {
					if (!world.isRemote) {
						playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock) train).getTrainName() + " can be pulled, don't forget to fuel it!"));
						playerEntity.addChatMessage(new ChatComponentText("Attach the BACK of this locomotive to the BACK of another locomotive. Otherwise you will encounter weird problems in general."));
					}
					((Locomotive) train).setCanBeAdjusted(true);
					((Locomotive) train).canBePulled = true;
					if (((Locomotive) train).mtcStatus != 0 && ((Locomotive) train).trainIsWMTCSupported()) {
						((Locomotive) train).disconnectFromServer();
					}
				} else {
					if (!world.isRemote) {
						playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock) train).getTrainName() + " can pull"));
					}
					((Locomotive) train).setCanBeAdjusted(false);
					((Locomotive) train).canBePulled = false;
				}

				return true;
			}

			if (!world.isRemote) {
				if (!train.isAttaching) {
					train.isAttaching = true;
					playerEntity.addChatMessage(new ChatComponentText("Attaching mode on for: " + ((EntityRollingStock) train).getTrainName()));
					itemstack.damageItem(1, playerEntity);

				} else {
					playerEntity.addChatMessage(new ChatComponentText("Reset, click again to couple new cart to this one"));
					train.Link1 = -1;
					train.Link2 = -1;
					if (train.cartLinked1 != null && train.cartLinked1.Link1 == train.getUniqueTrainID())
						train.cartLinked1.Link1 = -1;
					if (train.cartLinked1 != null && train.cartLinked1.Link2 == train.getUniqueTrainID())
						train.cartLinked1.Link2 = -1;
					if (train.cartLinked2 != null && train.cartLinked2.Link1 == train.getUniqueTrainID())
						train.cartLinked2.Link1 = -1;
					if (train.cartLinked2 != null && train.cartLinked2.Link2 == train.getUniqueTrainID())
						train.cartLinked2.Link2 = -1;

					if (train.cartLinked1 != null && train.cartLinked1.cartLinked1 != null && train.cartLinked1.cartLinked1.equals(train))
						train.cartLinked1.cartLinked1 = null;
					if (train.cartLinked1 != null && train.cartLinked1.cartLinked2 != null && train.cartLinked1.cartLinked2.equals(train))
						train.cartLinked1.cartLinked2 = null;
					if (train.cartLinked2 != null && train.cartLinked2.cartLinked2 != null && train.cartLinked2.cartLinked2.equals(train))
						train.cartLinked2.cartLinked2 = null;
					if (train.cartLinked2 != null && train.cartLinked2.cartLinked1 != null && train.cartLinked2.cartLinked1.equals(train))
						train.cartLinked2.cartLinked1 = null;


					train.cartLinked1 = null;
					train.cartLinked2 = null;
					train.isAttaching = false;
					train.isAttached = false;

					if (((EntityRollingStock) train).trainHandler != null) {
						((EntityRollingStock) train).trainHandler.resetTrain();
					}


					if (((EntityRollingStock) train).trainHandler != null && ((EntityRollingStock) train).trainHandler.getTrains().size() <= 1) {
						/** no more @RollingStocks in the train then remove the train object from the global list */
						EntityRollingStock.allTrains.remove(((EntityRollingStock) train).trainHandler);
						//System.out.println("Train is destroyed, remove it from the global array");
					}
				}
			}
			return true;

		} else {
			return false;
		}
	}

	public boolean onClickWithBrakeHandle(AbstractTrains entityRollingStock, ItemStack itemstack, EntityPlayer playerEntity, World world)
	{
		if (itemstack != null && itemstack.getItem() == ItemIDs.brakeStick.item && !world.isRemote
				&& (entityRollingStock instanceof Locomotive == false && entityRollingStock instanceof AbstractControlCar == false)
				&& (FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer()
					|| entityRollingStock.getTrainLockedFromPacket() == false
					|| entityRollingStock.getTransportOwner() == null
					|| entityRollingStock.getTransportOwner().equals("")
					|| entityRollingStock.getTransportOwner().equalsIgnoreCase(playerEntity.getDisplayName())
					|| entityRollingStock.isPlayerTrusted(playerEntity.getDisplayName())
					|| entityRollingStock.isPlayerTrustedToBreak(playerEntity.getDisplayName()))
		)
		{
			if (playerEntity.isSneaking())
			{
				if (((EntityRollingStock)entityRollingStock).parkingBrake)
				{
					if (!world.isRemote)
					{
						playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock)entityRollingStock).getTrainName() + " disengaged hand brake"));
					}
					else
					{
						Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(false, entityRollingStock.getEntityId()));
					}

					return true;
				}
				else
				{
					if (!world.isRemote)
					{
						playerEntity.addChatMessage(new ChatComponentText(((EntityRollingStock)entityRollingStock).getTrainName() + " engaged hand brake"));
					}
					else
					{
						Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(true, entityRollingStock.getEntityId()));
					}

					return true;
				}
			}

			return false;
		}

		return false;
	}

	public boolean onClickWithPaintBrush(AbstractTrains entityRollingStock, ItemStack itemStack, EntityPlayer entityPlayer, World world)
	{
		if (itemStack.getItem() instanceof ItemPaintbrushThing && entityPlayer.isSneaking())
		{
			if (entityRollingStock.acceptedColors != null && !entityRollingStock.acceptedColors.isEmpty()) {
				entityPlayer.openGui(Traincraft.instance, GuiIDs.PAINTBRUSH, entityPlayer.getEntityWorld(), entityRollingStock.getEntityId(), -1, (int) entityRollingStock.posZ);
			} else if (entityRollingStock.acceptsOverlayTextures()) {
				entityPlayer.openGui(Traincraft.instance, GuiIDs.OVERLAY_MENU, entityPlayer.getEntityWorld(), entityRollingStock.getEntityId(), -1, (int) entityPlayer.posZ);
			}

			if (entityRollingStock.acceptedColors != null && entityRollingStock.acceptedColors.isEmpty())
			{
				PostChatMessage(entityPlayer, "There are no other colors available.");
			}

			return true;
		}

		return false;
	}

	public boolean onClickWithInterchangeTransferReportBoard(AbstractTrains abstractTrain, ItemStack itemstack, EntityPlayer entityPlayer, World world)
	{
		if (entityPlayer.isSneaking() && itemstack != null && itemstack.getItem() == ItemIDs.interchangeTransferReportBoard.item)
		{
			if (((EntityRollingStock)abstractTrain).trainHandler != null)
			{
				Boolean isRemote = world.isRemote;
				if (isRemote == false)
				{
					if (!canOpenInterchangeReport(entityPlayer))
					{
						PostChatMessage(entityPlayer, "Please wait before opening another report");
						return true;
					}

					if (abstractTrain.trainHandler.getTrains().size() > INTERCHANGE_REPORT_MAX_ROWS)
					{
						PostChatMessage(entityPlayer, "Interchange report is too large to open");
						return true;
					}

					InterchangeReportDraft draft = new InterchangeTransferReportGenerator().CreateInterchangeTransferDraft(ItemInterchangeTransferReportBoard.getRailroadName(itemstack), abstractTrain.trainHandler, false);
					draft.boardSlot = entityPlayer.inventory.currentItem;
					if (draft.rows.size() > INTERCHANGE_REPORT_MAX_ROWS)
					{
						PostChatMessage(entityPlayer, "Interchange report is too large to open");
						return true;
					}

					PostChatMessage(entityPlayer, "Opening Interchange Report");
				}

				return true;
			}
		}

		return false;
	}

	private boolean canOpenInterchangeReport(EntityPlayer entityPlayer)
	{
		String key = entityPlayer.getUniqueID() != null ? entityPlayer.getUniqueID().toString() : entityPlayer.getDisplayName();
		long now = System.currentTimeMillis();
		Long lastOpen = interchangeReportLastOpen.get(key);
		if (lastOpen != null && now - lastOpen < INTERCHANGE_REPORT_COOLDOWN_MS)
		{
			return false;
		}

		interchangeReportLastOpen.put(key, now);
		return true;
	}
	private void PostChatMessage(EntityPlayer entityPlayer, String message)
	{
		entityPlayer.addChatMessage(new ChatComponentText(message));
	}
}
