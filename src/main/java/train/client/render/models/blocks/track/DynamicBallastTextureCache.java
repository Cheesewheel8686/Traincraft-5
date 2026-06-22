package train.client.render.models.blocks.track;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public final class DynamicBallastTextureCache
{
    private static final int MAX_DYNAMIC_BALLAST_TEXTURES = 128;
    private static final Map<String, ResourceLocation> DYNAMIC_BALLAST_TEXTURES = new LinkedHashMap<String, ResourceLocation>(MAX_DYNAMIC_BALLAST_TEXTURES, 0.75F, true)
    {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, ResourceLocation> eldest)
        {
            return size() > MAX_DYNAMIC_BALLAST_TEXTURES;
        }
    };

    private DynamicBallastTextureCache()
    {
    }

    public static ResourceLocation get(String iconName)
    {
        int separatorIndex = iconName.indexOf(':');
        if (separatorIndex >= 0)
        {
            return get(iconName.substring(0, separatorIndex).toLowerCase(), iconName.substring(separatorIndex + 1));
        }
        return get("minecraft", iconName);
    }

    public static ResourceLocation get(String domain, String texture)
    {
        String key = domain + ":" + texture;
        ResourceLocation location = DYNAMIC_BALLAST_TEXTURES.get(key);
        if (location == null)
        {
            location = new ResourceLocation(domain, "textures/blocks/" + texture + ".png");
            DYNAMIC_BALLAST_TEXTURES.put(key, location);
        }
        return location;
    }

    public static void clear()
    {
        DYNAMIC_BALLAST_TEXTURES.clear();
    }
}
