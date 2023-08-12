package net.azisaba.loreeditor.common.chat;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

public interface Component {
    @NotNull Component STATIC = getInstance(null);

    static @NotNull Component getInstance(@Nullable Object o) {
        return (Component) ReflectionUtil.getImplInstance("chat.Component", o);
    }

    @Nullable
    Component deserialize(@NotNull String input);

    @NotNull
    String serialize(@NotNull Component component);

    @NotNull
    List<?> getSiblings();

    void addSiblingText(@NotNull String text);

    @NotNull
    Component modifyStyle(@NotNull UnaryOperator<ChatModifier> action);
}
