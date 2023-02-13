package net.azisaba.rarity.common.util;

import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public interface ChatUtil {
    @NotNull
    ChatUtil INSTANCE = (ChatUtil) ReflectionUtil.newImplClassInstance("util.ChatUtil");

    @NotNull
    TextComponent createComponentWithHoverWithSuggestCommand(@NotNull String message, @NotNull String hoverText, @NotNull String suggestCommand);
}
