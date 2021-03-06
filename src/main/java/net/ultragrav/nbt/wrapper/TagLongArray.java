package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

@Getter
@AllArgsConstructor
public class TagLongArray extends Tag<long[]> {
    private long[] data;

    @Override
    public byte getTypeId() {
        return 12;
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeInt(data.length);
        for(long l : data) {
            gravSerializer.writeLong(l);
        }
    }

    public static TagLongArray deserialize(GravSerializer serializer) {
        long[] data = new long[serializer.readInt()];
        for(int i = 0; i < data.length; i++) {
            data[i] = serializer.readLong();
        }
        return new TagLongArray(data);
    }
}
