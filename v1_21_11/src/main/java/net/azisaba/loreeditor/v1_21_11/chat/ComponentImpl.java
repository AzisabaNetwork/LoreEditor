package net.azisaba.loreeditor.v1_21_11.chat;

import com.google.gson.Gson;
import com.mojang.serialization.JsonOps;
import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.chat.Component;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

@Reflected
public record ComponentImpl(MutableComponent handle) implements Component {
    private static final Gson GSON = new Gson();

    public ComponentImpl(@Nullable net.minecraft.network.chat.Component handle) {
        this(handle == null ? null : handle.copy());
    }

    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull ComponentImpl getInstance(@Nullable Object component) {
        return new ComponentImpl((net.minecraft.network.chat.Component) component);
    }

    @Override
    public @NotNull MutableComponent handle() {
        return Objects.requireNonNull(handle, "cannot reference handle in static context");
    }

    public static MutableComponent deserializeFromJson(@NotNull String input) {
        return ComponentSerialization.CODEC.decode(JsonOps.INSTANCE, GSON.toJsonTree(input)).getOrThrow().getFirst().copy();
    }

    public static String serializeToJson(@NotNull net.minecraft.network.chat.Component component) {
        return GSON.toJson(ComponentSerialization.CODEC.encodeStart(JsonOps.INSTANCE, component).getOrThrow());
    }

    @Override
    public @Nullable Component deserialize(@NotNull String input) {
        return getInstance(deserializeFromJson(input));
    }

    @Override
    public @NotNull String serialize(@NotNull Component component) {
        return serializeToJson(((ComponentImpl) component).handle());
    }

    @Override
    public @NotNull List<?> getSiblings() {
        return handle().getSiblings();
    }

    @Override
    public void addSiblingText(@NotNull String text) {
        handle().append(deserializeFromJson(text));
    }

    @Override
    public @NotNull Component modifyStyle(@NotNull UnaryOperator<ChatModifier> action) {
        ChatModifier cm = new ChatModifierImpl(handle().getStyle());
        Style newChatModifier = ((ChatModifierImpl) action.apply(cm)).handle();
        return getInstance(handle().setStyle(newChatModifier));
    }
}
