package net.azisaba.loreeditor.v1_16_R3.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.chat.Component;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.IChatMutableComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ComponentImpl implements Component {
    private final IChatMutableComponent handle;

    public ComponentImpl(@Nullable IChatMutableComponent handle) {
        this.handle = handle;
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ComponentImpl getInstance(@Nullable Object component) {
        if (component == null) return new ComponentImpl(null);
        if (component instanceof IChatMutableComponent) return new ComponentImpl((IChatMutableComponent) component);
        return new ComponentImpl(((IChatBaseComponent) component).mutableCopy());
    }

    public @NotNull IChatMutableComponent getHandle() {
        return Objects.requireNonNull(handle, "cannot reference handle in static context");
    }

    @Override
    public @Nullable Component deserialize(@NotNull String input) {
        return getInstance(IChatBaseComponent.ChatSerializer.a(input));
    }

    @Override
    public @NotNull String serialize(@NotNull Component component) {
        return IChatBaseComponent.ChatSerializer.a(((ComponentImpl) component).getHandle());
    }

    @Override
    public @NotNull List<?> getSiblings() {
        return getHandle().getSiblings();
    }

    @Override
    public void addSiblingText(@NotNull String text) {
        getHandle().addSibling(IChatBaseComponent.ChatSerializer.a(text));
    }

    @Override
    public @NotNull Component modifyStyle(@NotNull UnaryOperator<ChatModifier> action) {
        IChatMutableComponent component = getHandle();
        component.setChatModifier(((ChatModifierImpl) action.apply(new ChatModifierImpl(component.getChatModifier()))).getHandle());
        return getInstance(component);
    }
}
