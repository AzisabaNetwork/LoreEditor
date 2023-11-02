package net.azisaba.loreeditor.v1_20.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.util.Reflected;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ChatModifierImpl(net.minecraft.network.chat.ChatModifier handle) implements ChatModifier {
    public ChatModifierImpl {
        Objects.requireNonNull(handle, "handle");
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ChatModifierImpl getInstance(@NotNull Object handle) {
        return new ChatModifierImpl((net.minecraft.network.chat.ChatModifier) handle);
    }

    @Override
    public @NotNull net.minecraft.network.chat.ChatModifier handle() {
        return handle;
    }

    @Override
    public @NotNull ChatModifier setItalic(boolean italic) {
        return getInstance(handle.b(italic)); // setItalic
    }
}
