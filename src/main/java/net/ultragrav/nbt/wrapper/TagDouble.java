package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagDouble extends Tag<Double> {
    private double data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeDouble(data);
    }

    public static TagDouble deserialize(GravSerializer serializer) {
        return new TagDouble(serializer.readDouble());
    }

    @Override
    public Double getData() {
        return data;
    }
}
