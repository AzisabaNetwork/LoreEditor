package net.azisaba.loreeditor.api.event;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class RegisteredEvent<T> {
    private final @NotNull Plugin plugin;
    private final @NotNull Class<T> clazz;
    private final int priority;
    private final @NotNull Consumer<T> action;

    public RegisteredEvent(@NotNull Plugin plugin, @NotNull Class<T> clazz, int priority, @NotNull Consumer<T> action) {
        this.plugin = plugin;
        this.clazz = clazz;
        this.priority = priority;
        this.action = action;
    }

    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    public @NotNull Class<T> getClazz() {
        return clazz;
    }

    public int getPriority() {
        return priority;
    }

    public @NotNull Consumer<@NotNull T> getAction() {
        return action;
    }
}
