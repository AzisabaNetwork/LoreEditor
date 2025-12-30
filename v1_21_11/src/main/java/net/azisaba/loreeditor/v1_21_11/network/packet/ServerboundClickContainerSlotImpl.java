package net.azisaba.loreeditor.v1_21_11.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ServerboundClickContainerSlot;
import net.azisaba.loreeditor.v1_21_11.item.ItemStackImpl;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ServerboundClickContainerSlotImpl implements ServerboundClickContainerSlot {
    private final ServerboundContainerClickPacket handle;

    public ServerboundClickContainerSlotImpl(@NotNull ServerboundContainerClickPacket handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ServerboundClickContainerSlotImpl getInstance(@NotNull Object handle) {
        return new ServerboundClickContainerSlotImpl((ServerboundContainerClickPacket) handle);
    }

    @Override
    public @Nullable ItemStack getItem() {
        if (true) return null; // TODO: restore ItemStack?
        return ItemStackImpl.getInstance(handle.carriedItem());
    }
}
