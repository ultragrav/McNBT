package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
@Getter
public class TagString extends Tag<String> {

    private String data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeObject(data);
    }

    public static TagString deserialize(GravSerializer serializer) {
        return new TagString(serializer.readObject());
    }
}
