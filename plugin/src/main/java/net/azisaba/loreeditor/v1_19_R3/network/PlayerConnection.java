package net.azisaba.loreeditor.v1_19_R3.network;

import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.CastTo;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface PlayerConnection {
    @NotNull
    @CastTo(NetworkManager.class)
    @FieldGetter("h")
    NetworkManager getNetworkManager();
}
