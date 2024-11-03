package net.azisaba.loreeditor.v1_21_1.item.tag;

import net.azisaba.loreeditor.api.item.tag.StringTag;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringTagImpl extends TagImpl<net.minecraft.nbt.StringTag> implements StringTag {
    public StringTagImpl(@NotNull net.minecraft.nbt.StringTag handle) {
        super(handle);
    }

    @Contract("_ -> new")
    public static @NotNull StringTagImpl getInstance(@Nullable Object handle) {
        if (handle == null) {
            return new StringTagImpl(net.minecraft.nbt.StringTag.valueOf(""));
        }
        return new StringTagImpl((net.minecraft.nbt.StringTag) handle);
    }

    @Override
    public @NotNull StringTag valueOf(@NotNull String text) {
        return getInstance(net.minecraft.nbt.StringTag.valueOf(text));
    }
}
