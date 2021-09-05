package net.ultragrav.nbt.wrapper;

import net.ultragrav.serializer.GravSerializable;
import net.ultragrav.serializer.GravSerializer;

public abstract class Tag implements GravSerializable {
    public abstract byte getTypeId();

    public static void serializeTag(GravSerializer serializer, Tag tag) {
        serializer.writeByte((byte) tag.getTypeId());
        tag.serialize(serializer);
    }
    public static Tag deserializeTag(GravSerializer serializer) {
        byte id = serializer.readByte();
        return deserializeTag(id, serializer);
    }
    public static Tag deserializeTag(byte id, GravSerializer serializer) {
        switch (id) {
            case 0:
                return TagEnd.deserialize(serializer);
            case 1:
                return TagByte.deserialize(serializer);
            case 2:
                return TagShort.deserialize(serializer);
            case 3:
                return TagInt.deserialize(serializer);
            case 4:
                return TagLong.deserialize(serializer);
            case 5:
                return TagFloat.deserialize(serializer);
            case 6:
                return TagDouble.deserialize(serializer);
            case 7:
                return TagByteArray.deserialize(serializer);
            case 8:
                return TagString.deserialize(serializer);
            case 9:
                return TagList.deserialize(serializer);
            case 10:
                return TagCompound.deserialize(serializer);
            case 11:
                return TagIntArray.deserialize(serializer);
            case 12:
                return TagLongArray.deserialize(serializer);
        }
        throw new IllegalArgumentException("Invalid NBT tag id: " + id);
    }
}
