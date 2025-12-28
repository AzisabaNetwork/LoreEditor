package net.azisaba.loreeditor.v1_21_11.item;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.common.util.Reflected;
import net.azisaba.loreeditor.v1_21_11.chat.ComponentImpl;
import net.azisaba.loreeditor.v1_21_11.item.tag.CompoundTagImpl;
import net.azisaba.loreeditor.v1_21_11.item.tag.ListTagImpl;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
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
        return handleLore(new CompoundTagImpl(customData.getUnsafe()));
    }

    @SuppressWarnings("deprecation")
    @Override
    public @Nullable CompoundTag getTag() {
        CustomData customData = handle.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return handleLore(null);
        }
        return handleLore(new CompoundTagImpl(customData.getUnsafe()));
    }

    private CompoundTag handleLore(@Nullable CompoundTag base) {
        var handle = new net.minecraft.nbt.ListTag();
        ListTag listTag = new ListTagImpl(handle);
        if (handle().has(DataComponents.LORE)) {
            Objects.requireNonNull(handle().get(DataComponents.LORE)).lines()
                    .forEach(component -> handle.add(StringTag.valueOf(ComponentImpl.serializeToJson(component))));
        } else {
            return base;
        }
        if (handle.isEmpty()) {
            return base;
        }
        if (base == null) {
            base = new CompoundTagImpl(new net.minecraft.nbt.CompoundTag());
        }
        CompoundTag displayTag = base.getCompound("display");
        displayTag.set("Lore", listTag);
        base.set("display", displayTag);
        return base;
    }

    @Override
    public void setTag(@Nullable CompoundTag tag) {
        handle.set(DataComponents.CUSTOM_DATA, tag == null ? null : CustomData.of(((CompoundTagImpl) tag).getHandle()));
        if (tag != null) {
            if (!tag.hasKeyOfType("display", 10)) {
                handle.set(DataComponents.LORE, ItemLore.EMPTY);
                return;
            }
            CompoundTag displayTag = tag.getCompound("display");
            ListTag listTag = displayTag.getList("Lore", 8);
            if (listTag.size() > 0) {
                List<Component> lore =
                        ((ListTagImpl) listTag).getHandle()
                                .stream()
                                .map(t -> {
                                    if (t instanceof StringTag) {
                                        return t.asString().orElse("");
                                    } else {
                                        return t.toString();
                                    }
                                })
                                .map(ComponentImpl::deserializeFromJson)
                                .collect(Collectors.toUnmodifiableList());
                handle.set(DataComponents.LORE, new ItemLore(lore));
            } else {
                handle.set(DataComponents.LORE, ItemLore.EMPTY);
            }
        } else {
            handle.set(DataComponents.LORE, ItemLore.EMPTY);
        }
    }

    @Override
    public int getCount() {
        return handle.getCount();
    }

    @Override
    public @NotNull ItemStack copy() {
        org.bukkit.inventory.ItemStack originalStack = handle.getBukkitStack();
        org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(originalStack.getType(), handle.getCount());
        if (originalStack.hasItemMeta()) {
            stack.setItemMeta(originalStack.getItemMeta().clone());
        }
        return new ItemStackImpl(CraftItemStack.asNMSCopy(stack));
    }

    public DataComponentMap getComponents() {
        return handle.getComponents();
    }
}
