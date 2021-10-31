package net.ultragrav.nbt.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ultragrav.serializer.GravSerializer;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TagList extends Tag<List<Tag<?>>> {
    private List<Tag<?>> data;
    private byte type;

    public TagList(List<Tag<?>> data) {
        this.data = data;
        if (this.data.isEmpty()) {
            this.type = 0;
        } else {
            this.type = this.data.get(0).getTypeId();
        }
    }

    public TagList() {
        this(new ArrayList<>());
    }

    @Override
    public byte getTypeId() {
        return 9;
    }

    public void add(Tag<?> tag) {
        if (type == 0) {
            type = tag.getTypeId();
        }
        if (type != tag.getTypeId()) {
            throw new IllegalArgumentException("Tried to add tag of different type to NBT ");
        }
        data.add(tag);
    }

    @Override
    public void serialize(GravSerializer serializer) {
        if (this.data.isEmpty()) {
            this.type = 0;
        } else {
            this.type = this.data.get(0).getTypeId();
        }

        serializer.writeByte(this.type);
        serializer.writeInt(data.size());

        for (Tag<?> tag : data) {
            tag.serialize(serializer);
        }
    }

    public static TagList deserialize(GravSerializer serializer) {
        byte type = serializer.readByte();
        int length = serializer.readInt();

        List<Tag<?>> data = new ArrayList<>(length);
        for (int i = 0; i < length; i ++) {
            data.add(deserializeTag(type, serializer));
        }

        return new TagList(data);
    }
}
