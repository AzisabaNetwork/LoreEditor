package net.azisaba.loreeditor.v1_21_3.entity;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import net.azisaba.loreeditor.v1_21_3.network.ServerCommonPacketListenerImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;
import xyz.acrylicstyle.util.reflector.Reflector;
import xyz.acrylicstyle.util.reflector.ReflectorHandler;

public interface EntityPlayer {
    static @NotNull EntityPlayer getInstance(@Nullable Object o) {
        return Reflector.newReflector(null, EntityPlayer.class,
                new ReflectorHandler(ReflectionUtil.getNMSClass("net.minecraft.server.level.EntityPlayer"), o));
    }

    @NotNull
    @CastTo(value = ServerCommonPacketListenerImpl.class, createInstance = true)
    @FieldGetter("connection")
    ServerCommonPacketListenerImpl getPlayerConnection();
}
