package net.azisaba.loreeditor.v1_19_R3.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ClientboundSetSlot;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import net.azisaba.loreeditor.v1_19_R3.item.ItemStackImpl;
import net.minecraft.network.protocol.game.PacketPlayOutSetSlot;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClientboundSetSlotImpl implements ClientboundSetSlot {
    private final PacketPlayOutSetSlot handle;

    public ClientboundSetSlotImpl(PacketPlayOutSetSlot handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ClientboundSetSlotImpl getInstance(@NotNull Object handle) {
        return new ClientboundSetSlotImpl((PacketPlayOutSetSlot) handle);
    }

    public @NotNull PacketPlayOutSetSlot getHandle() {
        return handle;
    }

    @Override
    public @Nullable ItemStack getItem() {
        return ItemStackImpl.getInstance(ReflectionUtil.getField(handle, "f"));
    }
}
