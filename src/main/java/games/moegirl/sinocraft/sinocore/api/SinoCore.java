package games.moegirl.sinocraft.sinocore.api;

import net.minecraftforge.fml.ModList;

/**
 * SinoCore Public API
 */
public class SinoCore {

    /**
     * true if mod SinoFeast is loaded
     * @return whether SinoFeast is loaded
     */
    public static boolean hasFeastMod() {
        return ModList.get().isLoaded("sinofeast");
    }

    /**
     * true if mod SinoDivination is loaded
     * @return whether SinoDivination is loaded
     */
    public static boolean hasDivinationMod() {
        return ModList.get().isLoaded("sinodivination");
    }
}
