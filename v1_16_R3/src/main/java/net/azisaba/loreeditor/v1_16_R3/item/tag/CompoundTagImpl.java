package net.azisaba.loreeditor.v1_16_R3.item.tag;

import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.api.item.tag.Tag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompoundTagImpl extends TagImpl<NBTTagCompound> implements CompoundTag {
    public CompoundTagImpl(@NotNull NBTTagCompound handle) {
        super(handle);
    }

    @Contract("_ -> new")
    @Reflected
    public static @NotNull CompoundTagImpl getInstance(@Nullable Object tag) {
        if (tag == null) {
            return new CompoundTagImpl(new NBTTagCompound());
        }
        return new CompoundTagImpl((NBTTagCompound) tag);
    }

    @Override
    public @NotNull CompoundTag constructor() {
        return new CompoundTagImpl(new NBTTagCompound());
    }

    @Override
    public int size() {
        return getHandle().e();
    }

    @Override
    public boolean hasKeyOfType(@NotNull String key, int type) {
        return getHandle().hasKeyOfType(key, type);
    }

    @Override
    public void remove(@NotNull String key) {
        getHandle().remove(key);
    }

    @Override
    public boolean getBoolean(@NotNull String key) {
        return getHandle().getBoolean(key);
    }

    @Override
    public int getInt(@NotNull String key) {
        return getHandle().getInt(key);
    }

    @Override
    public @NotNull String getString(@NotNull String key) {
        return getHandle().getString(key);
    }

    @Override
    public @NotNull CompoundTag getCompound(@NotNull String key) {
        return new CompoundTagImpl(getHandle().getCompound(key));
    }

    @Override
    public @NotNull ListTag getList(@NotNull String key, int type) {
        return new ListTagImpl(getHandle().getList(key, type));
    }

    @Override
    public void setString(@NotNull String key, @NotNull String value) {
        getHandle().setString(key, value);
    }

    @Override
    public void setBoolean(@NotNull String key, boolean value) {
        getHandle().setBoolean(key, value);
    }

    @Override
    public void setInt(@NotNull String key, int value) {
        getHandle().setInt(key, value);
    }

    @Override
    public void set(@NotNull String key, @NotNull Tag value) {
        getHandle().set(key, ((TagImpl<?>) value).getHandle());
    }

    @Override
    public @Nullable Tag get(@NotNull String key) {
        return TagImpl.toTag(getHandle().get(key));
    }
}
