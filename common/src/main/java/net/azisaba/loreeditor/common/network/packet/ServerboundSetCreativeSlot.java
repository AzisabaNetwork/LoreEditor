package net.azisaba.loreeditor.common.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ServerboundSetCreativeSlot {
    static @NotNull ServerboundSetCreativeSlot getInstance(@Nullable Object o) {
        return (ServerboundSetCreativeSlot) ReflectionUtil.getImplInstance("network.packet.ServerboundSetCreativeSlot", o);
    }

    @Nullable
    ItemStack getItem();
}
