package net.azisaba.loreeditor.common.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ClientboundWindowItems {
    static @NotNull ClientboundWindowItems getInstance(@Nullable Object o) {
        return (ClientboundWindowItems) ReflectionUtil.getImplInstance("network.packet.ClientboundWindowItems", o);
    }

    /**
     * Returns the copy of items in the window. Modifications to the returned list will not affect the items in the
     * window (but modifying the item stacks in the list will).
     * @return items in the window
     */
    @NotNull
    List<ItemStack> getItems();

    void setItems(@NotNull List<ItemStack> items);
}
