package net.azisaba.loreeditor.common.util;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface ChannelUtil {
    @NotNull
    ChannelUtil INSTANCE = (ChannelUtil) ReflectionUtil.newImplClassInstance("util.ChannelUtil");

    void inject(@NotNull Plugin plugin, @NotNull Player player);

    void eject(@NotNull Player player);
}
