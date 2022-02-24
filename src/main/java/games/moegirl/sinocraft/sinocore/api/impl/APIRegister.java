package games.moegirl.sinocraft.sinocore.api.impl;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;

import java.lang.reflect.Field;

public class APIRegister {

    public static void register() {
        set(SinoCoreAPI.class, "crafting", Crafting.INSTANCE);
    }

    private static void set(Class<?> holder, String name, Object value) {
        try {
            Field field = holder.getField(name);
            field.setAccessible(true);
            field.set(null, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }
}
