package net.azisaba.loreeditor.v1_20.network;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;
import xyz.acrylicstyle.util.reflector.Reflector;
import xyz.acrylicstyle.util.reflector.ReflectorHandler;

public class ServerCommonPacketListenerImpl {
    private final IServerCommonPacketListenerImpl reflector;

    public ServerCommonPacketListenerImpl(Object o) {
        this.reflector = Reflector.newReflector(null, IServerCommonPacketListenerImpl.class,
                new ReflectorHandler(ReflectionUtil.getNMSClass("net.minecraft.server.network.ServerCommonPacketListenerImpl"), o));
    }

    public @NotNull NetworkManager getNetworkManager() {
        return reflector.getNetworkManager();
    }
}
