package net.azisaba.loreeditor.v1_21_11.item.tag;

import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.api.item.tag.Tag;
import net.azisaba.loreeditor.common.util.Reflected;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CompoundTagImpl extends TagImpl<net.minecraft.nbt.CompoundTag> implements CompoundTag {
    public CompoundTagImpl(@NotNull net.minecraft.nbt.CompoundTag handle) {
        super(handle);
    }

    @Contract("_ -> new")
    @Reflected
    public static @NotNull CompoundTagImpl getInstance(@Nullable Object tag) {
        if (tag == null) {
            return new CompoundTagImpl(new net.minecraft.nbt.CompoundTag());
        }
        return new CompoundTagImpl((net.minecraft.nbt.CompoundTag) tag);
    }

    @Override
    public @NotNull CompoundTag constructor() {
        return new CompoundTagImpl(new net.minecraft.nbt.CompoundTag());
    }

    @Override
    public int size() {
        return getHandle().size();
    }

    @Override
    public boolean hasKeyOfType(@NotNull String key, int type) {
        if (!getHandle().contains(key)) {
            return false;
        }
        return Objects.requireNonNull(getHandle().get(key)).getId() == type;
    }

    @Override
    public void remove(@NotNull String key) {
        getHandle().remove(key);
    }

    @Override
    public boolean getBoolean(@NotNull String key) {
        return getHandle().getBoolean(key).orElse(false);
    }

    @Override
    public int getInt(@NotNull String key) {
        return getHandle().getInt(key).orElse(0);
    }

    @Override
    public @NotNull String getString(@NotNull String key) {
        return getHandle().getString(key).orElse("");
    }

    @Override
    public @NotNull CompoundTag getCompound(@NotNull String key) {
        return new CompoundTagImpl(getHandle().getCompound(key).orElseGet(net.minecraft.nbt.CompoundTag::new));
    }

    @Override
    public @NotNull ListTag getList(@NotNull String key, int type) {
        var optional = getHandle().getList(key);
        if (optional.isPresent() && optional.get().identifyRawElementType() == type) {
            return new ListTagImpl(optional.get());
        }
        return new ListTagImpl(new net.minecraft.nbt.ListTag());
    }

    @Override
    public void setString(@NotNull String key, @NotNull String value) {
        getHandle().putString(key, value);
    }

    @Override
    public void setBoolean(@NotNull String key, boolean value) {
        getHandle().putBoolean(key, value);
    }

    @Override
    public void setInt(@NotNull String key, int value) {
        getHandle().putInt(key, value);
    }

    @Override
    public void set(@NotNull String key, @NotNull Tag value) {
        getHandle().put(key, ((TagImpl<?>) value).getHandle());
    }

    @Override
    public @Nullable Tag get(@NotNull String key) {
        return TagImpl.toTag(getHandle().get(key));
    }
}
