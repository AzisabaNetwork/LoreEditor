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

/**
 * Fired when an item is being displayed in the inventory.
 */
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

    /**
     * Get the player who is viewing the item
     * @return the viewer
     */
    public @NotNull Player getPlayer() {
        return player;
    }

    /**
     * Returns the item as a Bukkit ItemStack. Modifications to this item will <b>NOT</b> be reflected in the actual item.
     * @return the bukkit item
     */
    public @NotNull ItemStack getBukkitItem() {
        return bukkitItem;
    }

    /**
     * Returns the item tag of the item. Modifications to this tag will <b>NOT</b> be reflected in the actual item.
     * @return the item tag
     */
    public @NotNull CompoundTag getItemTag() {
        return itemTag;
    }

    /**
     * Add a lore to the item. This will be displayed in the result item.
     * @param component the lore to add
     */
    public void addLore(@NotNull Component component) {
        components.add(component);
    }

    /**
     * Returns the list of lore components to be added to the item.
     * @return the list of lore components
     */
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
