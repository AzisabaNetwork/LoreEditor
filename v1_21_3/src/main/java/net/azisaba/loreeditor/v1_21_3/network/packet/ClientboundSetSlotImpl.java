package net.azisaba.loreeditor.v1_21_3.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ClientboundSetSlot;
import net.azisaba.loreeditor.v1_21_3.item.ItemStackImpl;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClientboundSetSlotImpl implements ClientboundSetSlot {
    private final ClientboundContainerSetSlotPacket handle;

    public ClientboundSetSlotImpl(ClientboundContainerSetSlotPacket handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ClientboundSetSlotImpl getInstance(@NotNull Object handle) {
        return new ClientboundSetSlotImpl((ClientboundContainerSetSlotPacket) handle);
    }

    public @NotNull ClientboundContainerSetSlotPacket getHandle() {
        return handle;
    }

    @Override
    public @Nullable ItemStack getItem() {
        return ItemStackImpl.getInstance(handle.getItem());
    }

    @Override
    public void replaceItem(@NotNull ItemStack item) {
        if (getItem() == null) throw new IllegalStateException("Cannot replace null item (for now)");
        ((ItemStackImpl) getItem()).handle().restorePatch(((ItemStackImpl) item).handle().getComponentsPatch());
    }
}
