package net.azisaba.loreeditor.api.event;

import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ItemEvent {
    private final Player player;
    private final ItemStack bukkitItem;
    private final CompoundTag itemTag;
    private final List<Component> components = new ArrayList<>();

    public ItemEvent(@NotNull Player player, @NotNull ItemStack bukkitItem, @NotNull CompoundTag itemTag) {
        this.player = player;
        this.bukkitItem = bukkitItem;
        this.itemTag = itemTag;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull ItemStack getBukkitItem() {
        return bukkitItem;
    }

    public @NotNull CompoundTag getItemTag() {
        return itemTag;
    }

    public void addLore(@NotNull Component component) {
        components.add(component);
    }

    @Contract(pure = true)
    public @NotNull @UnmodifiableView List<Component> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public String toString() {
        return "ItemEvent{" +
                "player=" + player +
                ", bukkitItem=" + bukkitItem +
                ", itemTag=" + itemTag +
                '}';
    }
}
