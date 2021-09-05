package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@Getter
@AllArgsConstructor
public class TagShort extends Tag {
    private short data;

    @Override
    public byte getTypeId() {
        return 2;
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeShort(data);
    }

    public static TagShort deserialize(GravSerializer serializer) {
        return new TagShort(serializer.readShort());
    }
}
