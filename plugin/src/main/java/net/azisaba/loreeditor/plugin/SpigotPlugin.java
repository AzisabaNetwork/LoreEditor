package net.azisaba.loreeditor.plugin;

import net.azisaba.loreeditor.common.util.ChannelUtil;
import net.azisaba.loreeditor.plugin.listener.PlayerListener;
import net.azisaba.loreeditor.plugin.listener.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.util.reflector.Reflector;
import xyz.acrylicstyle.util.reflector.executor.MethodExecutorReflection;

public class SpigotPlugin extends JavaPlugin {
    @Override
    public void onLoad() {
        Reflector.classLoader = getClassLoader();
        Reflector.methodExecutor = new MethodExecutorReflection();
    }

    @Override
    public void onEnable() {
//        saveDefaultConfig();

        // register event handler
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PluginListener(), this);

        // inject packet pre handler
        for (Player player : Bukkit.getOnlinePlayers()) {
            ChannelUtil.INSTANCE.inject(this, player);
        }
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ChannelUtil.INSTANCE.eject(player);
        }
    }
}
