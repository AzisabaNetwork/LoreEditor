package net.azisaba.rarity.v1_15_R1.item.tag;

import net.azisaba.rarity.api.item.tag.StringTag;
import net.minecraft.server.v1_15_R1.NBTTagString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringTagImpl extends TagImpl<NBTTagString> implements StringTag {
    public StringTagImpl(@NotNull NBTTagString handle) {
        super(handle);
    }

    @Contract("_ -> new")
    public static @NotNull StringTagImpl getInstance(@Nullable Object handle) {
        if (handle == null) {
            return new StringTagImpl(NBTTagString.a(""));
        }
        return new StringTagImpl((NBTTagString) handle);
    }

    @Override
    public @NotNull StringTag valueOf(@NotNull String text) {
        return getInstance(NBTTagString.a(text));
    }
}
