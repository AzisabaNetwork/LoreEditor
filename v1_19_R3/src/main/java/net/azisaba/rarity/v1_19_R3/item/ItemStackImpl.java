package net.azisaba.rarity.v1_19_R3.item;

import net.azisaba.rarity.api.item.ItemStack;
import net.azisaba.rarity.api.item.tag.CompoundTag;
import net.azisaba.rarity.common.util.Reflected;
import net.azisaba.rarity.v1_19_R3.item.tag.CompoundTagImpl;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemStackImpl implements ItemStack {
    private final net.minecraft.world.item.ItemStack handle;

    public ItemStackImpl(net.minecraft.world.item.ItemStack handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    public @NotNull net.minecraft.world.item.ItemStack getHandle() {
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
        return new CompoundTagImpl(handle.v()); // getOrCreateTag
    }

    @Override
    public @Nullable CompoundTag getTag() {
        NBTTagCompound handle = this.handle.u(); // getTag
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
