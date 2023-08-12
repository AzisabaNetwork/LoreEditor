package net.azisaba.loreeditor.v1_19_R3.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.chat.Component;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.network.chat.IChatBaseComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ComponentImpl implements Component {
    private final IChatBaseComponent handle;

    public ComponentImpl(@Nullable IChatBaseComponent handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ComponentImpl getInstance(@Nullable Object component) {
        return new ComponentImpl((IChatBaseComponent) component);
    }

    public @NotNull IChatBaseComponent getHandle() {
        return Objects.requireNonNull(handle, "cannot reference handle in static context");
    }

    @Override
    public @Nullable Component deserialize(@NotNull String input) {
        return getInstance(IChatBaseComponent.ChatSerializer.a(input)); // deserialize
    }

    @Override
    public @NotNull String serialize(@NotNull Component component) {
        return IChatBaseComponent.ChatSerializer.a(((ComponentImpl) component).getHandle()); // serialize
    }

    @Override
    public @NotNull List<?> getSiblings() {
        return getHandle().c(); // getSiblings
    }

    @Override
    public void addSiblingText(@NotNull String text) {
        getHandle().a(IChatBaseComponent.ChatSerializer.a(text)); // addSibling
    }

    @Override
    public @NotNull Component modifyStyle(@NotNull UnaryOperator<ChatModifier> action) {
        ChatModifier cm = new ChatModifierImpl(getHandle().a()); // getChatModifier
        net.minecraft.network.chat.ChatModifier newChatModifier = ((ChatModifierImpl) action.apply(cm)).getHandle();
        return getInstance(getHandle().a(newChatModifier)); // setChatModifier
    }
}
