package net.azisaba.loreeditor.v1_16_R3.item.tag;

import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.api.item.tag.Tag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ListTagImpl extends TagImpl<NBTTagList> implements ListTag {
    public ListTagImpl(NBTTagList handle) {
        super(handle);
    }

    @Contract("_ -> new")
    @Reflected
    public static @NotNull ListTagImpl getInstance(@Nullable Object tag) {
        if (tag == null) {
            return new ListTagImpl(new NBTTagList());
        }
        return new ListTagImpl((NBTTagList) tag);
    }

    @Override
    public @NotNull ListTag constructor() {
        return new ListTagImpl(new NBTTagList());
    }

    @Override
    public int size() {
        return getHandle().size();
    }

    @Override
    public @NotNull Tag removeAt(int index) {
        return TagImpl.toTag(getHandle().remove(index));
    }

    @Override
    public void add(int index, @NotNull Tag tag) {
        getHandle().add(index, ((TagImpl<?>) tag).getHandle());
    }
}
