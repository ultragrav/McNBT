package net.ultragrav.nbt.conversion;

import net.ultragrav.nbt.wrapper.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class NBTConversion {
    /**
     * Wrap a Minecraft NBT tag using this library
     *
     * @param mcTag Minecraft tag
     * @return Wrapped tag
     */
    public static <T extends Tag> T wrapTag(Object mcTag) throws IllegalAccessException {
        if (mcTag == null) throw new IllegalArgumentException("mcTag cannot be null");

        String clName = mcTag.getClass().getName();
        String[] cls = clName.split("\\.");
        String version = cls[3];
        String tagType = cls[4];

        Class<?> baseClass = mcTag.getClass().getSuperclass();

        switch (tagType) {
            case "NBTTagByte":
                return (T) new TagByte(Util.getFirstField(mcTag, byte.class));
            case "NBTTagByteArray":
                return (T) new TagByteArray(Util.getFirstField(mcTag, byte[].class));
            case "NBTTagCompound":
                Map<?, ?> map = Util.getFirstField(mcTag, Map.class);
                Map<String, Tag> map2 = new HashMap<>();
                for (Map.Entry<?, ?> ent : map.entrySet()) {
                    map2.put((String) ent.getKey(), wrapTag(ent.getValue()));
                }
                return (T) new TagCompound(map2);
            case "NBTTagDouble":
                return (T) new TagDouble(Util.getFirstField(mcTag, double.class));
            case "NBTTagEnd":
                return (T) TagEnd.instance;
            case "NBTTagFloat":
                return (T) new TagFloat(Util.getFirstField(mcTag, float.class));
            case "NBTTagInt":
                return (T) new TagInt(Util.getFirstField(mcTag, int.class));
            case "NBTTagIntArray":
                return (T) new TagIntArray(Util.getFirstField(mcTag, int[].class));
            case "NBTTagList":
                List<?> list = Util.getFirstField(mcTag, List.class);
                List<Tag> list2 = new ArrayList<>();
                for (Object obj : list) {
                    list2.add(wrapTag(obj));
                }
                return (T) new TagList(list2);
            case "NBTTagLong":
                return (T) new TagLong(Util.getFirstField(mcTag, long.class));
            case "NBTTagLongArray":
                return (T) new TagLongArray(Util.getFirstField(mcTag, long[].class));
            case "NBTTagShort":
                return (T) new TagShort(Util.getFirstField(mcTag, short.class));
            case "NBTTagString":
                return (T) new TagString(Util.getFirstField(mcTag, String.class));
        }
        throw new IllegalArgumentException("Invalid Minecraft NBT Tag");
    }

    /**
     * Unwrap a wrapped NBT tag
     *
     * @param tag     Tag
     * @param version Server version in package format, such as {@code v1_12_R1}
     * @param <T>     Unwrapped tag
     * @return
     */
    public static <T> T unwrapTag(Tag tag, String version) {
        try {
            Class<T> clazz = (Class<T>) Class.forName("net.minecraft.server." + version + ".NBT" + tag.getClass().getSimpleName());
            Class<? extends Tag> tagClass = tag.getClass();
            if (tag instanceof TagCompound) {
                Map<String, Tag> dat = ((TagCompound) tag).getData();
                Map ret = new HashMap();
                for (Map.Entry<String, Tag> ent : dat.entrySet()) {
                    ret.put(ent.getKey(), unwrapTag(ent.getValue(), version));
                }
                T t = Util.construct(clazz);
                Util.setFirstField(t, ret);
                return t;
            } else if (tag instanceof TagList) {
                List<Tag> list = ((TagList) tag).getData();
                List ret = new ArrayList();
                for (Tag t : list) {
                    ret.add(unwrapTag(t, version));
                }
                T t = Util.construct(clazz);
                Util.setFirstField(t, ret);
                return t;
            } else {
                try {
                    Method getDataMethod = tagClass.getDeclaredMethod("getData");

                    Object dat = getDataMethod.invoke(tag);
                    return Util.construct(clazz, dat);
                } catch (NoSuchMethodException ex) {
                    if (tag instanceof TagEnd) {
                        return Util.construct(clazz);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
