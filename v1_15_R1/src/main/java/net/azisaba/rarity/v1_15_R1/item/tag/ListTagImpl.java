package net.azisaba.rarity.v1_15_R1.item.tag;

import net.azisaba.rarity.api.item.tag.ListTag;
import net.azisaba.rarity.api.item.tag.Tag;
import net.azisaba.rarity.common.util.Reflected;
import net.minecraft.server.v1_15_R1.NBTTagList;
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
