package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TagList extends Tag<List<Tag>> {
    private List<Tag> data;

    public TagList() {
        data = new ArrayList<>();
    }

    @Override
    public void serialize(GravSerializer gravSerializer) {
        gravSerializer.writeObject(data);
    }

    public static TagList deserialize(GravSerializer serializer) {
        return new TagList(serializer.readObject());
    }
}
