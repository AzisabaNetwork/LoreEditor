package net.azisaba.loreeditor.v1_21_1.network;

import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface IServerCommonPacketListenerImpl {
    @NotNull
    @CastTo(NetworkManager.class)
    @FieldGetter("connection")
    NetworkManager getNetworkManager();
}
