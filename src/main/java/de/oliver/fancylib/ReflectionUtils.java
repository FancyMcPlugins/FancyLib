package de.oliver.fancylib;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Object getValue(Object instance, String name) {
        Object result = null;

        try {
            Field field = instance.getClass().getDeclaredField(name);

            field.setAccessible(true);
            result = field.get(instance);
            field.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Object getStaticValue(Class clazz, String name) {
        Object result = null;

        try {
            Field field = clazz.getDeclaredField(name);

            field.setAccessible(true);
            result = field.get(clazz);
            field.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void setValue(Object instance, String name, Object value) {
        try {
            Field field = instance.getClass().getDeclaredField(name);

            field.setAccessible(true);
            field.set(instance, value);
            field.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
