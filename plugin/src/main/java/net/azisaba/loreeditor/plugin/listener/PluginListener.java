package net.azisaba.loreeditor.plugin.listener;

import net.azisaba.loreeditor.api.event.EventBus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class PluginListener implements Listener {
    @EventHandler
    public void onPluginDisable(PluginDisableEvent e) {
        EventBus.INSTANCE.unregister(e.getPlugin());
    }
}
