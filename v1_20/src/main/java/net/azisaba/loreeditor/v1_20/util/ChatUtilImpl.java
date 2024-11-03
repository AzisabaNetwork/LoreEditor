package net.azisaba.loreeditor.v1_20.util;

import net.azisaba.loreeditor.common.util.ChatUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ChatUtilImpl implements ChatUtil {
    @Override
    public @NotNull TextComponent createComponentWithHoverWithSuggestCommand(@NotNull String message, @NotNull String hoverText, @NotNull String suggestCommand) {
        TextComponent component = new TextComponent(message);
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hoverText)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestCommand));
        return component;
    }
}
