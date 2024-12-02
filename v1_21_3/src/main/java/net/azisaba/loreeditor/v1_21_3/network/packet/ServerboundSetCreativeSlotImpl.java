package net.azisaba.loreeditor.v1_21_3.network.packet;

import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.network.packet.ServerboundSetCreativeSlot;
import net.azisaba.loreeditor.v1_21_3.item.ItemStackImpl;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ServerboundSetCreativeSlotImpl implements ServerboundSetCreativeSlot {
    private final ServerboundSetCreativeModeSlotPacket handle;

    public ServerboundSetCreativeSlotImpl(ServerboundSetCreativeModeSlotPacket handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ServerboundSetCreativeSlotImpl getInstance(Object handle) {
        return new ServerboundSetCreativeSlotImpl((ServerboundSetCreativeModeSlotPacket) handle);
    }

    @Override
    public @Nullable ItemStack getItem() {
        return ItemStackImpl.getInstance(handle.itemStack());
    }
}
