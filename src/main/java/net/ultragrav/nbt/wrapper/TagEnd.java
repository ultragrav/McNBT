package net.ultragrav.nbt.wrapper;

import net.ultragrav.serializer.GravSerializer;

public class TagEnd extends Tag<Void> {
    public static final TagEnd instance = new TagEnd();

    @Override
    public void serialize(GravSerializer gravSerializer) {
    }

    public static TagEnd deserialize(GravSerializer serializer) {
        return new TagEnd();
    }

    @Override
    public Void getData() {
        return null;
    }
}
