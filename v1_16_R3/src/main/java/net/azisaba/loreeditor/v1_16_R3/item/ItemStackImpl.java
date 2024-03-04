package net.azisaba.loreeditor.v1_16_R3.item;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.azisaba.loreeditor.v1_16_R3.item.tag.CompoundTagImpl;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemStackImpl implements ItemStack {
    private final net.minecraft.server.v1_16_R3.ItemStack handle;

    public ItemStackImpl(net.minecraft.server.v1_16_R3.ItemStack handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    public @NotNull net.minecraft.server.v1_16_R3.ItemStack getHandle() {
        return handle;
    }

    @Contract("_ -> new")
    @Reflected
    public static @NotNull ItemStackImpl getInstance(@NotNull Object item) {
        Objects.requireNonNull(item, "item");
        return new ItemStackImpl((net.minecraft.server.v1_16_R3.ItemStack) item);
    }

    @Override
    public @NotNull CompoundTag getOrCreateTag() {
        return new CompoundTagImpl(handle.getOrCreateTag());
    }

    @Override
    public @Nullable CompoundTag getTag() {
        NBTTagCompound handle = this.handle.getTag();
        return handle == null ? null : new CompoundTagImpl(handle);
    }

    @Override
    public void setTag(@Nullable CompoundTag tag) {
        handle.setTag(tag == null ? null : ((CompoundTagImpl) tag).getHandle());
    }

    @Override
    public int getCount() {
        return handle.getCount();
    }
}
