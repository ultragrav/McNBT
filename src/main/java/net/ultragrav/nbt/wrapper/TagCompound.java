package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ultragrav.serializer.GravSerializer;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagCompound extends Tag {
    private Map<String, Tag> data = new HashMap<>();

    @Override
    public byte getTypeId() {
        return 10;
    }

    @Override
    public void serialize(GravSerializer serializer) {
        data.forEach((k, t) -> {
            serializer.writeByte(t.getTypeId());
            serializer.writeString(k);
            t.serialize(serializer);
        });
        serializer.writeByte((byte) 0);
    }

    public static TagCompound deserialize(GravSerializer serializer) {
        Map<String, Tag> tags = new HashMap<>();
        byte id;
        while ((id = serializer.readByte()) != 0) {
            String key = serializer.readString();
            Tag value = deserializeTag(id, serializer);
            tags.put(key, value);
        }
        return new TagCompound(tags);
    }
}
