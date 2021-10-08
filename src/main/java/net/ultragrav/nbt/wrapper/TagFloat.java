package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagFloat extends Tag<Float> {
    private float data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeFloat(data);
    }

    public static TagFloat deserialize(GravSerializer serializer) {
        return new TagFloat(serializer.readFloat());
    }

    @Override
    public Float getData() {
        return data;
    }
}
