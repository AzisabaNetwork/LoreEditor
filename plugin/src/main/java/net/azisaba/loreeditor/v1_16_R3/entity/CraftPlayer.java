package net.azisaba.loreeditor.v1_16_R3.entity;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import net.azisaba.loreeditor.v1_15_R1.entity.EntityPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.Reflector;
import xyz.acrylicstyle.util.reflector.ReflectorHandler;

import java.util.Objects;

public interface CraftPlayer {
    static @NotNull CraftPlayer getInstance(@NotNull Player player) {
        return Reflector.newReflector(null, CraftPlayer.class,
                new ReflectorHandler(ReflectionUtil.getOBCClass("entity.CraftPlayer"), Objects.requireNonNull(player, "player")));
    }

    @NotNull
    @CastTo(EntityPlayer.class)
    EntityPlayer getHandle();
}
