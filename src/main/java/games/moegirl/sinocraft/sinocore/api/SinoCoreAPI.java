package games.moegirl.sinocraft.sinocore.api;

import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * SinoCore Public API
 */
public class SinoCoreAPI {

    private static final Map<String, Object> IMPL_MAP = new HashMap<>();

    /**
     * Crafting API,
     *
     * @return Crafting API
     */
    public static ICrafting getCraftings() {
        return getApiImpl(ICrafting.IMPL);
    }

    @SuppressWarnings("unchecked")
    private static  <T> T getApiImpl(String classPath) {
        return (T) IMPL_MAP.computeIfAbsent(classPath, k -> {
            try {
                Class<?> aClass = SinoCoreAPI.class.getClassLoader().loadClass(classPath);
                if (aClass.isEnum()) {
                    // Enum singleton
                    return aClass.getEnumConstants()[0];
                } else {
                    // constructor
                    return aClass.getConstructor().newInstance();
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
