package net.azisaba.loreeditor.v1_21_3.chat;

import net.azisaba.loreeditor.common.chat.ChatModifier;
import net.azisaba.loreeditor.common.chat.Component;
import net.azisaba.loreeditor.common.util.Reflected;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

@Reflected
public record ComponentImpl(MutableComponent handle) implements Component {
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
        RegistryAccess registries = ((CraftServer) Bukkit.getServer()).getServer().registryAccess();
        return net.minecraft.network.chat.Component.Serializer.fromJson(input, registries);
    }

    public static String serializeToJson(@NotNull net.minecraft.network.chat.Component component) {
        RegistryAccess registries = ((CraftServer) Bukkit.getServer()).getServer().registryAccess();
        return net.minecraft.network.chat.Component.Serializer.toJson(component, registries);
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
