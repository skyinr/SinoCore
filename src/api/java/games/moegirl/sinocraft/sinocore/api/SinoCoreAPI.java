package games.moegirl.sinocraft.sinocore.api;

import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

/**
 * SinoCore Public API
 */
public class SinoCoreAPI {

    public static final boolean DEBUG_MODE;

    public static final Logger LOGGER = LogManager.getLogger();
    private static ICrafting crafting;
    private static boolean isInitialized = false;

    static {
        String property = System.getProperty("sun.java.command");
        DEBUG_MODE = property != null && property.contains("MOD_DEV");
    }

    /**
     * Crafting API,
     *
     * @return Crafting API
     */
    public static ICrafting getCrafting() {
        return crafting;
    }

    public static void _loadCoreApi(Consumer<ApiLoader> consumer) {
        if (!isInitialized && "sinocore".equals(ModLoadingContext.get().getActiveNamespace())) {
            consumer.accept(new ApiLoaderImpl());
            isInitialized = true;
        } else {
            throw new RuntimeException("DON'T reset SinoCore API!!!");
        }
    }

    private static class ApiLoaderImpl implements ApiLoader {

        @Override
        public void setCrafting(ICrafting api) {
            crafting = api;
        }
    }
}
