package net.azisaba.loreeditor.v1_19_R3.item.tag;

import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.api.item.tag.Tag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.nbt.NBTTagCompound;
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
        return getHandle().f(); // size
    }

    @Override
    public boolean hasKeyOfType(@NotNull String key, int type) {
        return getHandle().b(key, type); // hasKeyOfType
    }

    @Override
    public void remove(@NotNull String key) {
        getHandle().r(key); // remove
    }

    @Override
    public boolean getBoolean(@NotNull String key) {
        return getHandle().q(key); // getBoolean
    }

    @Override
    public int getInt(@NotNull String key) {
        return getHandle().h(key); // getInt
    }

    @Override
    public @NotNull String getString(@NotNull String key) {
        return getHandle().l(key); // getString
    }

    @Override
    public @NotNull CompoundTag getCompound(@NotNull String key) {
        return new CompoundTagImpl(getHandle().p(key)); // getCompound
    }

    @Override
    public @NotNull ListTag getList(@NotNull String key, int type) {
        return new ListTagImpl(getHandle().c(key, type)); // getList
    }

    @Override
    public void setString(@NotNull String key, @NotNull String value) {
        getHandle().a(key, value); // setString
    }

    @Override
    public void setBoolean(@NotNull String key, boolean value) {
        getHandle().a(key, value); // setBoolean
    }

    @Override
    public void setInt(@NotNull String key, int value) {
        getHandle().a(key, value); // setInt
    }

    @Override
    public void set(@NotNull String key, @NotNull Tag value) {
        getHandle().a(key, ((TagImpl<?>) value).getHandle()); // set
    }

    @Override
    public @Nullable Tag get(@NotNull String key) {
        return TagImpl.toTag(getHandle().c(key)); // get
    }
}
