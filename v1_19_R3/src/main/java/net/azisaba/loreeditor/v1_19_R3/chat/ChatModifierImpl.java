package net.azisaba.loreeditor.v1_19_R3.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.util.Reflected;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChatModifierImpl implements ChatModifier {
    private final net.minecraft.network.chat.ChatModifier handle;

    public ChatModifierImpl(net.minecraft.network.chat.ChatModifier handle) {
        Objects.requireNonNull(handle, "handle");
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ChatModifierImpl getInstance(@NotNull Object handle) {
        return new ChatModifierImpl((net.minecraft.network.chat.ChatModifier) handle);
    }

    public @NotNull net.minecraft.network.chat.ChatModifier getHandle() {
        return handle;
    }

    @Override
    public @NotNull ChatModifier setItalic(boolean italic) {
        return getInstance(handle.b(italic)); // setItalic
    }
}
