package games.moegirl.sinocraft.sinocore.api.impl;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;

import java.lang.reflect.Field;

public class APIRegister {

    public static void register() {
        setCore("crafting", Crafting.INSTANCE);
        setCore("mixin", Mixins.INSTANCE);
    }

    private static void set(Class<?> holder, String name, Object value) {
        try {
            Field field = holder.getField(name);
            field.setAccessible(true);
            field.set(null, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    private static void setCore(String name, Object value) {
        set(SinoCoreAPI.class, name, value);
    }
}
