package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@Getter
@AllArgsConstructor
public class TagIntArray extends Tag {
    private int[] data;

    @Override
    public byte getTypeId() {
        return 11;
    }

    @Override
    public void serialize(GravSerializer serializer) {
        serializer.writeInt(data.length);
        for (int i : data) serializer.writeInt(i);
    }

    public static TagIntArray deserialize(GravSerializer serializer) {
        int length = serializer.readInt();
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = serializer.readInt();
        }
        return new TagIntArray(data);
    }
}
