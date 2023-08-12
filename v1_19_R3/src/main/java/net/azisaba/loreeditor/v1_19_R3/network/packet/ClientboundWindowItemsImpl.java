package net.azisaba.loreeditor.v1_19_R3.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ClientboundWindowItems;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import net.azisaba.loreeditor.v1_19_R3.item.ItemStackImpl;
import net.minecraft.network.protocol.game.PacketPlayOutWindowItems;
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
        for (Object o : ((List<?>) ReflectionUtil.getField(getHandle(), "c"))) {
            items.add(ItemStackImpl.getInstance(o));
        }
        return items;
    }
}
