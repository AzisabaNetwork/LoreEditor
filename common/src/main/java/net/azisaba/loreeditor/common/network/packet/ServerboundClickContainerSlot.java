package net.azisaba.loreeditor.common.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ServerboundClickContainerSlot {
    static @NotNull ServerboundClickContainerSlot getInstance(@Nullable Object o) {
        return (ServerboundClickContainerSlot) ReflectionUtil.getImplInstance("network.packet.ServerboundClickContainerSlot", o);
    }

    @Nullable
    ItemStack getItem();
}
