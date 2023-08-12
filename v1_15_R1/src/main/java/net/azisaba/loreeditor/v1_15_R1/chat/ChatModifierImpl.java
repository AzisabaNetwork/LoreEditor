package net.azisaba.loreeditor.v1_15_R1.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.util.Reflected;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChatModifierImpl implements ChatModifier {
    private final net.minecraft.server.v1_15_R1.ChatModifier handle;

    public ChatModifierImpl(net.minecraft.server.v1_15_R1.ChatModifier handle) {
        Objects.requireNonNull(handle, "handle");
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ChatModifierImpl getInstance(@NotNull Object handle) {
        return new ChatModifierImpl((net.minecraft.server.v1_15_R1.ChatModifier) handle);
    }

    public @NotNull net.minecraft.server.v1_15_R1.ChatModifier getHandle() {
        return handle;
    }

    @Override
    public @NotNull ChatModifier setItalic(boolean italic) {
        return getInstance(handle.setItalic(italic));
    }
}
