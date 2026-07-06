package train.common.core.network.AdminBook;

import com.jcirmodelsquad.tcjcir.features.autotrain.BogiePathfinding;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import train.common.api.AbstractTrains;
import train.common.library.Info;

import java.util.HashSet;
import java.util.Set;

public class PacketAdminBookToggleChunkLoading implements IMessage
{
    boolean loadChunks;

    public  PacketAdminBookToggleChunkLoading()
    {

    }

    public PacketAdminBookToggleChunkLoading(boolean loadChunks)
    {
        this.loadChunks = loadChunks;
    }

    /**
     * Convert from the supplied buffer into your specific message type
     *
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.loadChunks = buf.readBoolean();
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(this.loadChunks);
    }

    public static class Handler implements IMessageHandler<PacketAdminBookToggleChunkLoading, IMessage>
    {

        /**
         * The admin-book button is intentionally implemented as a server-side purge rather than
         * as another client-side report of locomotive state.
         *
         * <p>Forge enforces the chunkloading limit against every ticket owned by the mod id, not
         * against Traincraft's visible locomotive toggle. A server can therefore hit the 200-ticket
         * ceiling while the admin book reports no locomotive with chunkloading enabled. That happens
         * when tickets are held by non-locomotive Traincraft entities, restored persistent tickets,
         * or loaded entities with stale tickets in dimensions other than the current player's.</p>
         *
         * <p>This handler treats the button as an emergency cleanup command for active loaded worlds.
         * The persistent-ticket pass is scoped to {@link Info#modID}. Loaded Traincraft entities also
         * release their own stored ticket references, which are expected to be Traincraft tickets.</p>
         */
        @Override
        public IMessage onMessage(PacketAdminBookToggleChunkLoading message, MessageContext context)
        {
            EntityPlayerMP admin = context.getServerHandler().playerEntity;
            PurgeResult result = purgeTraincraftChunkTickets();
            admin.addChatMessage(new ChatComponentText(result.getSummaryMessage()));

            return null;
        }

        /**
         * Releases Traincraft chunkloading tickets reachable from loaded Traincraft entities or
         * Forge's persistent forced-chunk map for currently loaded worlds.
         *
         * <p>The purge has two passes per dimension:</p>
         * <p>1. Visit loaded entities first so Traincraft-owned fields such as flag 7 and
         * {@code chunkTicket} are cleared along with the Forge ticket. This prevents the entity from
         * immediately believing it still owns a valid ticket after Forge has discarded it.</p>
         * <p>2. Visit Forge's persistent chunk map afterward to catch tickets that belong to
         * Traincraft but are not currently attached to a loaded {@link AbstractTrains} or known
         * pathfinder entity.</p>
         *
         * <p>This does not edit offline region data or delete {@code forcedchunks.dat}. If a
         * dimension is not loaded, Forge has not exposed that dimension's tickets to this command
         * yet.</p>
         */
        private PurgeResult purgeTraincraftChunkTickets()
        {
            PurgeResult result = new PurgeResult();
            Set<Ticket> releasedTickets = new HashSet<Ticket>();

            for (WorldServer world : DimensionManager.getWorlds()) {
                if (world == null) {
                    continue;
                }

                result.dimensionsScanned++;
                purgeLoadedEntities(world, releasedTickets, result);
                purgeRemainingPersistentTickets(world, releasedTickets, result);
            }

            return result;
        }

        /**
         * Clears ticket state on loaded Traincraft entities that can hold ticket references directly.
         *
         * <p>Normal rolling stock and locomotives inherit {@link AbstractTrains}; experimental
         * AutoTrain pathfinders do not, so both entity types must be checked explicitly. This is the
         * central reason the old admin-book implementation could miss tickets: it only inspected
         * loaded {@code Locomotive} instances in the player's current world.</p>
         */
        private void purgeLoadedEntities(WorldServer world, Set<Ticket> releasedTickets, PurgeResult result)
        {
            for (Object value : world.loadedEntityList) {
                if (!(value instanceof Entity)) {
                    continue;
                }

                Entity entity = (Entity) value;
                if (entity instanceof AbstractTrains) {
                    purgeTrain((AbstractTrains) entity, releasedTickets, result);
                }
                else if (entity instanceof BogiePathfinding) {
                    purgePathfinder((BogiePathfinding) entity, result);
                }
            }
        }

        /**
         * Clears one loaded train's chunkloading state.
         *
         * <p>If flag 7 is enabled, {@link AbstractTrains#setPacketChunkLoading(boolean)} is used so
         * the entity follows its normal "turn chunkloading off" path and clears both the flag and the
         * ticket reference. If the flag is already false but the train still has a ticket reference,
         * the ticket is stale from Traincraft's point of view and is released directly.</p>
         */
        private void purgeTrain(AbstractTrains train, Set<Ticket> releasedTickets, PurgeResult result)
        {
            Ticket ticket = train.getTicket();

            if (train.IsChunkLoading()) {
                result.trainFlagsCleared++;
                train.setPacketChunkLoading(false);
                if (ticket != null && releasedTickets.add(ticket)) {
                    result.ticketsReleased++;
                }
            }
            else if (releaseTicket(ticket, releasedTickets)) {
                train.setTicket(null);
                result.ticketsReleased++;
            }
        }

        /**
         * Clears one experimental AutoTrain pathfinder.
         *
         * <p>These pathfinders are not {@link AbstractTrains} and are invisible to the locomotive
         * chunkloading flag. They can still allocate Forge entity tickets, so the admin purge has to
         * call their ticket release hook directly.</p>
         */
        private void purgePathfinder(BogiePathfinding pathfinder, PurgeResult result)
        {
            if (pathfinder.releaseChunkTicket()) {
                result.pathfinderTicketsReleased++;
            }
        }

        /**
         * Releases Traincraft tickets still present in Forge's persistent chunk map after loaded
         * entities have had a chance to clean up their own fields.
         */
        private void purgeRemainingPersistentTickets(WorldServer world, Set<Ticket> releasedTickets, PurgeResult result)
        {
            for (Ticket ticket : ForgeChunkManager.getPersistentChunksFor(world).values()) {
                if (isTraincraftTicket(ticket) && releaseTicket(ticket, releasedTickets)) {
                    result.ticketsReleased++;
                }
            }
        }

        /**
         * Releases train and persistent-map tickets at most once during a purge.
         *
         * <p>The same Forge ticket can be reachable from both an {@link AbstractTrains} field and
         * the persistent chunk map. The {@code releasedTickets} set avoids double-release calls and
         * keeps the admin chat summary from over-counting.</p>
         */
        private boolean releaseTicket(Ticket ticket, Set<Ticket> releasedTickets)
        {
            if (ticket == null || !releasedTickets.add(ticket)) {
                return false;
            }
            ForgeChunkManager.releaseTicket(ticket);
            return true;
        }

        /**
         * Keeps the emergency command scoped to Traincraft.
         */
        private boolean isTraincraftTicket(Ticket ticket)
        {
            return ticket != null && Info.modID.equals(ticket.getModId());
        }

        /**
         * Small value object for the admin chat report.
         *
         * <p>The counts are operational diagnostics: they tell the admin whether the purge found
         * normal train state, pathfinder state, persistent Forge state, or nothing at all.</p>
         */
        private static class PurgeResult
        {
            private int dimensionsScanned;
            private int ticketsReleased;
            private int trainFlagsCleared;
            private int pathfinderTicketsReleased;

            private String getSummaryMessage()
            {
                return "Released Traincraft chunk tickets. Dimensions scanned: " + dimensionsScanned
                        + ", tickets released: " + ticketsReleased
                        + ", train flags cleared: " + trainFlagsCleared
                        + ", pathfinder tickets released: " + pathfinderTicketsReleased + ".";
            }
        }
    }
}
