package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@AllArgsConstructor
public class TagLong extends Tag<Long> {
    private long data;

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeObject(data);
    }

    public static TagLong deserialize(GravSerializer serializer) {
        return new TagLong(serializer.readObject());
    }

    @Override
    public Long getData() {
        return data;
    }
}
