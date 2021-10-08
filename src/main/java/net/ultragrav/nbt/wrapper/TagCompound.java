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
public class TagCompound extends Tag<Map<String, Tag>> {
    private Map<String, Tag> data = new HashMap<>();

    @Override
    public void serialize(GravSerializer serializer) {
        serializer.writeInt(data.size());
        data.forEach((k, t) -> {
            serializer.writeString(k);
            serializeTag(serializer, t);
        });
    }

    public static TagCompound deserialize(GravSerializer serializer) {
        int amount = serializer.readInt();
        Map<String, Tag> tags = new HashMap<>();
        for(int i = 0; i < amount; i++) {
            String name = serializer.readString();
            tags.put(name, deserializeTag(serializer));
        }
        TagCompound tag = new TagCompound();
        tag.data = tags;
        return tag;
    }
}
