package net.azisaba.loreeditor.api.item;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemStack {
    static @NotNull ItemStack getInstance(@Nullable Object o) {
        if (o instanceof org.bukkit.inventory.ItemStack) {
            throw new IllegalArgumentException("This method does not accept org.bukkit.inventory.ItemStack");
        }
        return (ItemStack) ReflectionUtil.getImplInstance("item.ItemStack", o);
    }

    /**
     * Get or create a tag if it does not exist.
     * @return the tag
     */
    @NotNull
    CompoundTag getOrCreateTag();

    /**
     * Get the tag if it exists.
     * @return the tag or null if it does not exist
     */
    @Nullable
    CompoundTag getTag();

    /**
     * Set the tag.
     * @param tag the tag
     */
    void setTag(@Nullable CompoundTag tag);

    /**
     * Returns the amount of the item stack.
     * @return the amount
     */
    int getCount();

    @NotNull
    ItemStack copy();
}
