package net.azisaba.loreeditor.v1_21_1.item;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.azisaba.loreeditor.v1_21_1.chat.ComponentImpl;
import net.azisaba.loreeditor.v1_21_1.item.tag.CompoundTagImpl;
import net.azisaba.loreeditor.v1_21_1.item.tag.ListTagImpl;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull CompoundTag getOrCreateTag() {
        CustomData customData = handle.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            customData = CustomData.of(new net.minecraft.nbt.CompoundTag());
            handle.set(DataComponents.CUSTOM_DATA, customData);
        }
        return new CompoundTagImpl(customData.getUnsafe());
    }

    @SuppressWarnings("deprecation")
    @Override
    public @Nullable CompoundTag getTag() {
        CustomData customData = handle.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return null;
        }
        return new CompoundTagImpl(customData.getUnsafe());
    }

    @Override
    public void setTag(@Nullable CompoundTag tag) {
        handle.set(DataComponents.CUSTOM_DATA, tag == null ? null : CustomData.of(((CompoundTagImpl) tag).getHandle()));
        if (tag != null) {
            if (!tag.hasKeyOfType("display", 10)) {
                return;
            }
            CompoundTag displayTag = tag.getCompound("display");
            ListTag listTag = displayTag.getList("Lore", 8);
            if (listTag.size() > 0) {
                List<Component> lore =
                        ((ListTagImpl) listTag).getHandle()
                                .stream()
                                .map(Tag::getAsString)
                                .map(ComponentImpl::deserializeFromJson)
                                .collect(Collectors.toUnmodifiableList());
                handle.set(DataComponents.LORE, new ItemLore(lore));
            }
        }
    }

    @Override
    public int getCount() {
        return handle.getCount();
    }

    @Override
    public @NotNull ItemStack copy() {
        return new ItemStackImpl(handle.copy());
    }
}
