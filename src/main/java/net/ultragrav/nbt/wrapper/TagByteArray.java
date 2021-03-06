package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagByteArray extends Tag<byte[]> {
    @Getter
    private byte[] data;

    @Override
    public byte getTypeId() {
        return 7;
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeByteArray(data);
    }

    public static TagByteArray deserialize(GravSerializer serializer) {
        return new TagByteArray(serializer.readByteArray());
    }
}
