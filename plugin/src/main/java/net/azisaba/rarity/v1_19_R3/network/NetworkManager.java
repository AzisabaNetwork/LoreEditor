package net.azisaba.rarity.v1_19_R3.network;

import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import xyz.acrylicstyle.util.reflector.FieldGetter;

public interface NetworkManager {
    @NotNull
    @FieldGetter("m")
    Channel getChannel();
}
