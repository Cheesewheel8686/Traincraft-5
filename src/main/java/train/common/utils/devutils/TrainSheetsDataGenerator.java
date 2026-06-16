package train.common.utils.devutils;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.library.register.ITrainRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class TrainSheetsDataGenerator
{
    public TrainSheetsDataGenerator()
    {
        StringBuilder tsv = new StringBuilder();
        List<Map.Entry<Item, ITrainRecord>> list = new ArrayList<Map.Entry<Item, ITrainRecord>>(Traincraft.traincraftRegistry.getAllTrains().entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Item, ITrainRecord>>()
        {
            @Override
            public int compare(Map.Entry<Item, ITrainRecord> e1,
                               Map.Entry<Item, ITrainRecord> e2)
            {
                // Compare by entity class name
                String c1 = e1.getValue().getEntityClass().getSimpleName();
                String c2 = e2.getValue().getEntityClass().getSimpleName();

                int cmp = c1.compareTo(c2);
                if (cmp != 0) return cmp;

                // Tie-breaker by item name, required for stability
                return e1.getKey().getUnlocalizedName()
                        .compareTo(e2.getKey().getUnlocalizedName());
            }
        });

        // Header row
        tsv.append("ItemName\tInternalName\tEntityClass\tTexturePrefix\n");

        try
        {
            for (Map.Entry<Item, ITrainRecord> entry : list)
            {
                Item item = entry.getKey();
                ITrainRecord record = entry.getValue();

                String name = item.getUnlocalizedName();
                String internalName = record.getInternalName();
                String className = record.getEntityClass().getName();

                World world = null;

                String texturePrefix = Traincraft.traincraftRegistry
                        .getTrainRenderRecord(
                                record.getEntityClass(),
                                (AbstractTrains) record.getEntityClass()
                                        .getConstructor(World.class)
                                        .newInstance(world)
                        )
                        .getTexturePrefix();

                tsv.append(name).append("\t")
                        .append(internalName).append("\t")
                        .append(className).append("\t")
                        .append(texturePrefix).append("\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            File configDir = Loader.instance().getConfigDir();
            File outDir = new File(configDir, "../../devdata");

            if (!outDir.exists())
            {
                outDir.mkdirs();
            }

            File outFile = new File(outDir, "Trains-PrefixData.tsv");

            String newData = tsv.toString();

            if (!outFile.exists() || !fileContentsEqual(outFile, newData))
            {
                writeStringToFile(outFile, newData);
                System.out.println("[TrainSheetsDataGenerator] Updated: " + outFile.getAbsolutePath());
            }
            else
            {
                System.out.println("[TrainSheetsDataGenerator] No changes: " + outFile.getAbsolutePath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean fileContentsEqual(File file, String newData)
    {
        try
        {
            byte[] existingBytes = readAllBytes(file);
            byte[] newBytes = newData.getBytes("UTF-8");

            return Arrays.equals(existingBytes, newBytes);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private static byte[] readAllBytes(File file) throws IOException
    {
        FileInputStream in = null;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();

        try
        {
            in = new FileInputStream(file);

            byte[] buffer = new byte[8192];
            int read;

            while ((read = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, read);
            }

            return out.toByteArray();
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }

    private static void writeStringToFile(File file, String data) throws IOException
    {
        Writer writer = null;

        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            writer.write(data);
        }
        finally
        {
            if (writer != null)
            {
                writer.close();
            }
        }
    }
}