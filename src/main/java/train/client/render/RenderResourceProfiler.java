package train.client.render;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Level;
import fexcraft.fvtm.BOBRollingStockModel;
import tmt.FVTMFormatBase;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class RenderResourceProfiler
{
    private static final boolean ENABLED = true;
    private static final long LOG_INTERVAL_NS = 5_000_000_000L;
    private static long intervalStart = System.nanoTime();

    private static long rollingStockRenderTimeNs;
    private static long passengerRenderTimeNs;
    private static long maxRollingStockRenderTimeNs;
    private static long maxPassengerRenderTimeNs;
    private static int rollingStockRenders;
    private static int passengerRenders;
    private static int guiRollingStockRenders;
    private static int batchSourceAttempts;
    private static int batchSourceSuccesses;
    private static int batchSourceEntries;
    private static int batchImmediateCalls;
    private static int batchImmediateEntries;
    private static int batchCacheHits;
    private static int batchCacheMisses;
    private static int batchCacheRebuilds;
    private static int batchCallEntries;
    private static int batchCompiledEntries;
    private static Field debugFpsField;
    private static boolean triedDebugFpsField;

    private static final Map<String, Integer> rollingStockCounts = new HashMap<String, Integer>();
    private static final Map<String, Long> rollingStockTimes = new HashMap<String, Long>();
    private static final Map<String, Integer> passengerCounts = new HashMap<String, Integer>();
    private static final Map<String, Long> passengerTimes = new HashMap<String, Long>();
    private static final Map<String, Integer> batchSourceCounts = new HashMap<String, Integer>();
    private static final Map<String, Long> batchSourceEntryCounts = new HashMap<String, Long>();
    private static final Map<String, Long> batchSourceTimes = new HashMap<String, Long>();

    private RenderResourceProfiler()
    {
    }

    public static long begin()
    {
        return ENABLED ? System.nanoTime() : 0L;
    }

    public static void endRollingStock(EntityRollingStock cart, long startNs, boolean renderModeGUI)
    {
        if (!ENABLED || startNs == 0L)
        {
            return;
        }

        long elapsedNs = System.nanoTime() - startNs;
        rollingStockRenderTimeNs += elapsedNs;
        maxRollingStockRenderTimeNs = Math.max(maxRollingStockRenderTimeNs, elapsedNs);
        rollingStockRenders++;
        if (renderModeGUI)
        {
            guiRollingStockRenders++;
        }

        String key = cart == null ? "unknown" : cart.getClass().getSimpleName();
        addCount(rollingStockCounts, key);
        addTime(rollingStockTimes, key, elapsedNs);

        if (cart instanceof AbstractPassengerCar)
        {
            passengerRenderTimeNs += elapsedNs;
            maxPassengerRenderTimeNs = Math.max(maxPassengerRenderTimeNs, elapsedNs);
            passengerRenders++;
            addCount(passengerCounts, key);
            addTime(passengerTimes, key, elapsedNs);
        }

        logIfDue();
    }

    public static void recordBatchSource(String source, Object owner, int entries, boolean rendered)
    {
        recordBatchSource(source, owner, entries, 0, rendered);
    }

    public static void recordBatchSource(String source, Object owner, int entries, int placements, boolean rendered)
    {
        if (!ENABLED)
        {
            return;
        }
        batchSourceAttempts++;
        batchSourceEntries += entries;
        if (rendered)
        {
            batchSourceSuccesses++;
        }
        String ownerName = profilerOwnerName(owner);
        String key = batchSourceKey(source, ownerName, placements, rendered);
        addCount(batchSourceCounts, key);
        addLong(batchSourceEntryCounts, key, entries);
    }

    public static long beginBatchSource()
    {
        return ENABLED ? System.nanoTime() : 0L;
    }

    public static void endBatchSource(String source, Object owner, long startNs, boolean rendered)
    {
        endBatchSource(source, owner, 0, startNs, rendered);
    }

    public static void endBatchSource(String source, Object owner, int placements, long startNs, boolean rendered)
    {
        if (!ENABLED || startNs == 0L)
        {
            return;
        }
        String ownerName = profilerOwnerName(owner);
        addTime(batchSourceTimes, batchSourceKey(source, ownerName, placements, rendered), System.nanoTime() - startNs);
    }

    private static String batchSourceKey(String source, String ownerName, boolean rendered)
    {
        return batchSourceKey(source, ownerName, 0, rendered);
    }

    private static String batchSourceKey(String source, String ownerName, int placements, boolean rendered)
    {
        return source + ":" + ownerName + (placements > 0 ? " placements=" + placements : "") + (rendered ? "" : " skipped");
    }

    private static String profilerOwnerName(Object owner)
    {
        if (owner == null)
        {
            return "unknown";
        }
        if (owner instanceof BOBRollingStockModel)
        {
            FVTMFormatBase baseModel = ((BOBRollingStockModel)owner).getBaseModel();
            return "BOB:" + profilerOwnerName(baseModel);
        }
        if (owner instanceof FVTMFormatBase)
        {
            FVTMFormatBase model = (FVTMFormatBase)owner;
            if (model.name != null && !model.name.isEmpty() && !"unnamed model".equalsIgnoreCase(model.name))
            {
                return model.name;
            }
        }
        return owner.getClass().getSimpleName();
    }

    public static void recordBatchImmediate(int entries)
    {
        if (!ENABLED)
        {
            return;
        }
        batchImmediateCalls++;
        batchImmediateEntries += entries;
    }

    public static void recordBatchCache(boolean hit, boolean stale, int entries)
    {
        if (!ENABLED)
        {
            return;
        }
        if (hit)
        {
            batchCacheHits++;
        }
        else if (stale)
        {
            batchCacheRebuilds++;
        }
        else
        {
            batchCacheMisses++;
        }
        batchCallEntries += entries;
        if (!hit)
        {
            batchCompiledEntries += entries;
        }
    }

    private static void addCount(Map<String, Integer> counts, String key)
    {
        Integer count = counts.get(key);
        counts.put(key, count == null ? 1 : count + 1);
    }

    private static void addTime(Map<String, Long> times, String key, long elapsedNs)
    {
        Long time = times.get(key);
        times.put(key, time == null ? elapsedNs : time + elapsedNs);
    }

    private static void addLong(Map<String, Long> values, String key, long amount)
    {
        Long value = values.get(key);
        values.put(key, value == null ? amount : value + amount);
    }

    private static void logIfDue()
    {
        long now = System.nanoTime();
        if (now - intervalStart < LOG_INTERVAL_NS)
        {
            return;
        }

        double seconds = (now - intervalStart) / 1_000_000_000.0D;
        String message = "[TC RenderProfiler] fps=" + getDebugFps()
                + " seconds=" + format(seconds)
                + " rollingStock=" + rollingStockRenders
                + " passengerCars=" + passengerRenders
                + " guiRollingStock=" + guiRollingStockRenders
                + " rollingAvgMs=" + format(avgMs(rollingStockRenderTimeNs, rollingStockRenders))
                + " rollingMaxMs=" + format(nsToMs(maxRollingStockRenderTimeNs))
                + " passengerAvgMs=" + format(avgMs(passengerRenderTimeNs, passengerRenders))
                + " passengerMaxMs=" + format(nsToMs(maxPassengerRenderTimeNs))
                + " batchSources=" + batchSourceSuccesses + "/" + batchSourceAttempts
                + " batchEntries=" + batchSourceEntries
                + " batchImmediate=" + batchImmediateCalls + "/" + batchImmediateEntries
                + " batchCache=hit" + batchCacheHits + "/miss" + batchCacheMisses + "/stale" + batchCacheRebuilds
                + " batchCallEntries=" + batchCallEntries
                + " batchCompiledEntries=" + batchCompiledEntries
                + " topRollingStock=" + topEntries(rollingStockCounts, rollingStockTimes)
                + " topPassengerCars=" + topEntries(passengerCounts, passengerTimes)
                + " topBatchSources=" + topBatchEntries(batchSourceCounts, batchSourceEntryCounts, batchSourceTimes);

        Traincraft.tcLog.log(Level.INFO, message);
        System.out.println(message);
        reset(now);
    }

    private static double avgMs(long timeNs, int count)
    {
        return count == 0 ? 0.0D : nsToMs(timeNs) / count;
    }

    private static double nsToMs(long timeNs)
    {
        return timeNs / 1_000_000.0D;
    }

    private static String topEntries(Map<String, Integer> counts, Map<String, Long> times)
    {
        Map<String, Integer> countCopy = new HashMap<String, Integer>(counts);
        Map<String, Long> timeCopy = new HashMap<String, Long>(times);
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 3; i++)
        {
            String key = topKey(countCopy);
            if (key == null)
            {
                break;
            }

            if (i > 0)
            {
                result.append(", ");
            }

            int count = countCopy.remove(key);
            long timeNs = timeCopy.containsKey(key) ? timeCopy.remove(key) : 0L;
            result.append(key)
                    .append(" count=")
                    .append(count)
                    .append(" avgMs=")
                    .append(format(avgMs(timeNs, count)));
        }
        result.append("]");
        return result.toString();
    }

    private static String topKey(Map<String, Integer> counts)
    {
        String topKey = null;
        int topCount = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet())
        {
            if (entry.getValue() > topCount)
            {
                topKey = entry.getKey();
                topCount = entry.getValue();
            }
        }
        return topKey;
    }

    private static String format(double value)
    {
        return String.format("%.2f", value);
    }

    private static int getDebugFps()
    {
        try
        {
            if (!triedDebugFpsField)
            {
                triedDebugFpsField = true;
                debugFpsField = Minecraft.class.getDeclaredField("debugFPS");
                debugFpsField.setAccessible(true);
            }
            return debugFpsField == null ? -1 : debugFpsField.getInt(null);
        }
        catch (Exception ignored)
        {
            debugFpsField = null;
            return -1;
        }
    }

    private static void reset(long now)
    {
        intervalStart = now;
        rollingStockRenderTimeNs = 0L;
        passengerRenderTimeNs = 0L;
        maxRollingStockRenderTimeNs = 0L;
        maxPassengerRenderTimeNs = 0L;
        rollingStockRenders = 0;
        passengerRenders = 0;
        guiRollingStockRenders = 0;
        batchSourceAttempts = 0;
        batchSourceSuccesses = 0;
        batchSourceEntries = 0;
        batchImmediateCalls = 0;
        batchImmediateEntries = 0;
        batchCacheHits = 0;
        batchCacheMisses = 0;
        batchCacheRebuilds = 0;
        batchCallEntries = 0;
        batchCompiledEntries = 0;
        rollingStockCounts.clear();
        rollingStockTimes.clear();
        passengerCounts.clear();
        passengerTimes.clear();
        batchSourceCounts.clear();
        batchSourceEntryCounts.clear();
        batchSourceTimes.clear();
    }

    private static String topBatchEntries(Map<String, Integer> counts, Map<String, Long> entryCounts, Map<String, Long> times)
    {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 5; i++)
        {
            String key = topKey(counts);
            if (key == null)
            {
                break;
            }

            if (i > 0)
            {
                result.append(", ");
            }

            int count = counts.remove(key);
            Long entries = entryCounts.remove(key);
            Long timeNs = times.remove(key);
            long totalEntries = entries == null ? 0L : entries;
            long totalTimeNs = timeNs == null ? 0L : timeNs;
            result.append(key)
                    .append(" count=")
                    .append(count)
                    .append(" avgEntries=")
                    .append(count == 0 ? 0 : totalEntries / count)
                    .append(" avgMs=")
                    .append(format(avgMs(totalTimeNs, count)));
        }
        result.append("]");
        return result.toString();
    }
}
