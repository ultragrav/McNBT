package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
@Getter
public class TagFloat extends Tag {
    private float data;

    @Override
    public byte getTypeId() {
        return 5;
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeFloat(data);
    }

    public static TagFloat deserialize(GravSerializer serializer) {
        return new TagFloat(serializer.readFloat());
    }
}
