package net.azisaba.loreeditor.v1_16_R3.network;

import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface PlayerConnection {
    @NotNull
    @CastTo(NetworkManager.class)
    @FieldGetter("networkManager")
    NetworkManager getNetworkManager();
}
