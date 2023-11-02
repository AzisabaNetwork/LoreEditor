package net.azisaba.loreeditor.v1_20.item;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.azisaba.loreeditor.v1_20.item.tag.CompoundTagImpl;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record ItemStackImpl(net.minecraft.world.item.ItemStack handle) implements ItemStack {
    public ItemStackImpl(net.minecraft.world.item.ItemStack handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    @Override
    public @NotNull net.minecraft.world.item.ItemStack handle() {
        return handle;
    }

    @Contract("_ -> new")
    @Reflected
    public static @NotNull ItemStackImpl getInstance(@NotNull Object item) {
        Objects.requireNonNull(item, "item");
        return new ItemStackImpl((net.minecraft.world.item.ItemStack) item);
    }

    @Override
    public @NotNull CompoundTag getOrCreateTag() {
        return new CompoundTagImpl(handle.w()); // getOrCreateTag
    }

    @Override
    public @Nullable CompoundTag getTag() {
        NBTTagCompound handle = this.handle.v(); // getTag
        return handle == null ? null : new CompoundTagImpl(handle);
    }

    @Override
    public void setTag(@Nullable CompoundTag tag) {
        handle.c(tag == null ? null : ((CompoundTagImpl) tag).getHandle()); // setTag
    }

    @Override
    public int getCount() {
        return handle.K(); // getCount (probably)
    }
}
