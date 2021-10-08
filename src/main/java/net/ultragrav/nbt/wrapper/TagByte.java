package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagByte extends Tag<Byte> {
    private byte data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeByte(data);
    }

    public static TagByte deserialize(GravSerializer serializer) {
        return new TagByte(serializer.readByte());
    }

    @Override
    public Byte getData() {
        return data;
    }
}
