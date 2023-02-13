package net.azisaba.rarity.v1_15_R1.network.packet;

import net.azisaba.rarity.api.item.ItemStack;
import net.azisaba.rarity.common.network.packet.ClientboundWindowItems;
import net.azisaba.rarity.common.util.ReflectionUtil;
import net.azisaba.rarity.v1_15_R1.item.ItemStackImpl;
import net.minecraft.server.v1_15_R1.PacketPlayOutWindowItems;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClientboundWindowItemsImpl implements ClientboundWindowItems {
    private final PacketPlayOutWindowItems handle;

    public ClientboundWindowItemsImpl(@NotNull PacketPlayOutWindowItems handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ClientboundWindowItemsImpl getInstance(Object handle) {
        return new ClientboundWindowItemsImpl((PacketPlayOutWindowItems) handle);
    }

    public @NotNull PacketPlayOutWindowItems getHandle() {
        return handle;
    }

    @Override
    public @NotNull List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();
        for (Object o : ((List<?>) ReflectionUtil.getField(getHandle(), "b"))) {
            items.add(ItemStackImpl.getInstance(o));
        }
        return items;
    }
}
