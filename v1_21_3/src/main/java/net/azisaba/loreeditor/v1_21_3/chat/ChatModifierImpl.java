package net.azisaba.loreeditor.v1_21_3.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ChatModifierImpl(Style handle) implements ChatModifier {
    public ChatModifierImpl {
        Objects.requireNonNull(handle, "handle");
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ChatModifierImpl getInstance(@NotNull Object handle) {
        return new ChatModifierImpl((Style) handle);
    }

    @Override
    public @NotNull Style handle() {
        return handle;
    }

    @Override
    public @NotNull ChatModifier setItalic(boolean italic) {
        return getInstance(handle.withItalic(italic)); // setItalic
    }
}
