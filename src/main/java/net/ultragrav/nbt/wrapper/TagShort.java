package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagShort extends Tag<Short> {
    private short data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeShort(data);
    }

    public static TagShort deserialize(GravSerializer serializer) {
        return new TagShort(serializer.readShort());
    }

    @Override
    public Short getData() {
        return data;
    }
}
