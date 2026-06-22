package train.client.render.models.blocks.track;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public final class TrackDisplayListCache
{
    private static final Map<String, Integer> DISPLAY_LISTS = new HashMap<>();

    private TrackDisplayListCache()
    {
    }

    public static int get(String modelPath)
    {
        Integer cachedList = DISPLAY_LISTS.get(modelPath);
        if (cachedList != null)
        {
            return cachedList;
        }

        ResourceLocation location = new ResourceLocation(modelPath);
        IModelCustom model = AdvancedModelLoader.loadModel(location);
        int displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, GL11.GL_COMPILE);
        model.renderAll();
        GL11.glEndList();

        DISPLAY_LISTS.put(modelPath, displayList);
        return displayList;
    }

    public static int get(ResourceLocation location)
    {
        return get(location.toString());
    }

    public static void clear()
    {
        for (Integer displayList : DISPLAY_LISTS.values())
        {
            GL11.glDeleteLists(displayList, 1);
        }
        DISPLAY_LISTS.clear();
    }
}
