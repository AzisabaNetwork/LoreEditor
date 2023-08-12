package net.azisaba.loreeditor.plugin.listener;

import net.azisaba.loreeditor.common.util.ChannelUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PlayerListener implements Listener {
    private final Plugin plugin;

    public PlayerListener(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {
        ChannelUtil.INSTANCE.inject(plugin, e.getPlayer());
    }
}
