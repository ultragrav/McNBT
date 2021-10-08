package net.ultragrav.nbt.wrapper;

import net.ultragrav.serializer.GravSerializable;
import net.ultragrav.serializer.GravSerializer;

public abstract class Tag<T> implements GravSerializable {
    public static void serializeTag(GravSerializer serializer, Tag<?> tag) {
        serializer.writeObject(tag);
    }
    public static <T> Tag<T> deserializeTag(GravSerializer serializer) {
        return serializer.readObject();
    }
    public abstract T getData();
}
