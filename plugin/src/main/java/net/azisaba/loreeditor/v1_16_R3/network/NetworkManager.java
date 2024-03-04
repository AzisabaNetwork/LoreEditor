package net.azisaba.loreeditor.v1_16_R3.network;

import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface NetworkManager {
    @NotNull
    @FieldGetter("channel")
    Channel getChannel();
}
