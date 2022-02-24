package games.moegirl.sinocraft.sinocore.api;

import cpw.mods.util.Lazy;
import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * SinoCore Public API
 */
public class SinoCoreAPI {

    private static ICrafting crafting;

    /**
     * Crafting API,
     *
     * @return Crafting API
     */
    public static ICrafting getCraftings() {
        return crafting;
    }
}
