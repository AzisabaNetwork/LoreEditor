package net.azisaba.loreeditor.v1_21_11.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ClientboundWindowItems;
import net.azisaba.loreeditor.v1_21_11.item.ItemStackImpl;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClientboundWindowItemsImpl implements ClientboundWindowItems {
    private final ClientboundContainerSetContentPacket handle;

    public ClientboundWindowItemsImpl(@NotNull ClientboundContainerSetContentPacket handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ClientboundWindowItemsImpl getInstance(Object handle) {
        return new ClientboundWindowItemsImpl((ClientboundContainerSetContentPacket) handle);
    }

    public @NotNull ClientboundContainerSetContentPacket getHandle() {
        return handle;
    }

    @Override
    public @NotNull List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();
        for (var o : handle.items()) {
            items.add(ItemStackImpl.getInstance(o));
        }
        return items;
    }

    @Override
    public void setItems(@NotNull List<ItemStack> items) {
        for (int i = 0; i < items.size(); i++) {
            handle.items().set(i, ((ItemStackImpl) items.get(i)).handle());
        }
    }
}
