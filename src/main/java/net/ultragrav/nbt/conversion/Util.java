package net.ultragrav.nbt.conversion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class Util {
    static <T> T getFirstField(Object obj, Class<T> type) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (type.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    return (T) field.get(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        throw new IllegalArgumentException("No such field found!");
    }

    static void setFirstField(Object obj, Object other) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isInstance(other) || field.getType().isAssignableFrom(unwrapPrimitive(other.getClass()))) {
                field.setAccessible(true);
                try {
                    field.set(obj, other);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        throw new IllegalArgumentException("No such field found!");
    }

    static <T> T construct(Class<T> clazz, Object... objs) {
        Class<?>[] cls = new Class[objs.length];
        for (int i = 0; i < objs.length; i++) {
            cls[i] = unwrapPrimitive(objs[i].getClass());
        }
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(cls);
            if (constructor != null) {
                constructor.setAccessible(true);
                return constructor.newInstance(objs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No such constructor");
    }

    private static Class<?> unwrapPrimitive(Class<?> clazz) {
        if (clazz == Boolean.class) return Boolean.TYPE;
        if (clazz == Character.class) return Character.TYPE;
        if (clazz == Byte.class) return Byte.TYPE;
        if (clazz == Short.class) return Short.TYPE;
        if (clazz == Integer.class) return Integer.TYPE;
        if (clazz == Long.class) return Long.TYPE;
        if (clazz == Float.class) return Float.TYPE;
        if (clazz == Double.class) return Double.TYPE;
        if (clazz == Void.class) return Void.TYPE;
        return clazz;
    }

    public static void main(String[] args) {
        int i = 0;
        Object o = i;
        System.out.println(int.class.isAssignableFrom(o.getClass()));
    }
}
