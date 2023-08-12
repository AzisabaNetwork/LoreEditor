package net.azisaba.loreeditor.common.chat;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ChatModifier {
    static @NotNull Component getInstance(@Nullable Object o) {
        return (Component) ReflectionUtil.getImplInstance("chat.Component", o);
    }

    @NotNull
    ChatModifier setItalic(boolean italic);
}
