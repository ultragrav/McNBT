package net.ultragrav.nbt.wrapper;

import net.ultragrav.serializer.GravSerializable;
import net.ultragrav.serializer.GravSerializer;

public abstract class Tag implements GravSerializable {
    public static void serializeTag(GravSerializer serializer, Tag tag) {
        serializer.writeObject(tag);
    }
    public static Tag deserializeTag(GravSerializer serializer) {
        return serializer.readObject();
    }
}
