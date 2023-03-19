package net.azisaba.rarity.v1_19_R3.entity;

import net.azisaba.rarity.common.util.ReflectionUtil;
import net.azisaba.rarity.v1_19_R3.network.PlayerConnection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;
import xyz.acrylicstyle.util.reflector.Reflector;
import xyz.acrylicstyle.util.reflector.ReflectorHandler;

public interface EntityPlayer {
    static @NotNull EntityPlayer getInstance(@Nullable Object o) {
        return Reflector.newReflector(null, EntityPlayer.class,
                new ReflectorHandler(ReflectionUtil.getNMSClass("EntityPlayer"), o));
    }

    @NotNull
    @CastTo(PlayerConnection.class)
    @FieldGetter("b")
    PlayerConnection getPlayerConnection();
}
