package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@Getter
@AllArgsConstructor
public class TagInt extends Tag {
    private int data;

    @Override
    public byte getTypeId() {
        return 3;
    }

    @Override
    public void serialize(GravSerializer serializer) {
        serializer.writeInt(data);
    }

    public static TagInt deserialize(GravSerializer serializer) {
        return new TagInt(serializer.readInt());
    }
}
