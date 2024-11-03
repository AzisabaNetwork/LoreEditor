package net.azisaba.loreeditor.common.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ClientboundSetSlot {
    static @NotNull ClientboundSetSlot getInstance(@Nullable Object o) {
        return (ClientboundSetSlot) ReflectionUtil.getImplInstance("network.packet.ClientboundSetSlot", o);
    }

    @Nullable
    ItemStack getItem();

    /**
     * Replaces the item tag/component. Other properties of the item stack are not guaranteed to be preserved.
     * @param item new item tag/component
     */
    void replaceItem(@NotNull ItemStack item);
}
