package net.azisaba.loreeditor.v1_21_3.network;

import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface NetworkManager {
    @NotNull
    @FieldGetter("channel")
    Channel getChannel();
}
