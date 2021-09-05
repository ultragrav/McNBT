package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
@Getter
public class TagDouble extends Tag {
    private double data;

    @Override
    public byte getTypeId() {
        return 6;
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeDouble(data);
    }

    public static TagDouble deserialize(GravSerializer serializer) {
        return new TagDouble(serializer.readDouble());
    }
}
