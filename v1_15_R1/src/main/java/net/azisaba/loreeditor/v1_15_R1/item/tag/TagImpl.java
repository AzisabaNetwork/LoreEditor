package net.azisaba.loreeditor.v1_15_R1.item.tag;

import net.azisaba.loreeditor.api.item.tag.Tag;
import net.minecraft.server.v1_15_R1.NBTBase;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;
import net.minecraft.server.v1_15_R1.NBTTagString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TagImpl<T extends NBTBase> implements Tag {
    private final T handle;

    public TagImpl(@NotNull T handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    public @NotNull T getHandle() {
        return Objects.requireNonNull(handle);
    }

    @Contract("null -> null; !null -> !null")
    public static Tag toTag(@Nullable NBTBase handle) {
        if (handle == null) {
            return null;
        } else if (handle instanceof NBTTagCompound) {
            return new CompoundTagImpl((NBTTagCompound) handle);
        } else if (handle instanceof NBTTagList) {
            return new ListTagImpl((NBTTagList) handle);
        } else if (handle instanceof NBTTagString) {
            return new StringTagImpl((NBTTagString) handle);
        } else {
            return new TagImpl<>(handle);
        }
    }

    @Override
    public @NotNull String asString() {
        return handle.asString();
    }

    @Override
    public String toString() {
        return handle.toString();
    }

    @Override
    public int hashCode() {
        return handle.hashCode();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag) && !(obj instanceof NBTBase)) {
            return false;
        }
        if (obj instanceof Tag) {
            obj = ((TagImpl<?>) obj).getHandle();
        }
        return handle.equals(obj);
    }
}
