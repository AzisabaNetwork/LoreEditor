package net.azisaba.loreeditor.v1_21_1.item.tag;

import net.azisaba.loreeditor.api.item.tag.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TagImpl<T extends net.minecraft.nbt.Tag> implements Tag {
    private final T handle;

    public TagImpl(@NotNull T handle) {
        this.handle = Objects.requireNonNull(handle, "handle");
    }

    public @NotNull T getHandle() {
        return Objects.requireNonNull(handle);
    }

    @Contract("null -> null; !null -> !null")
    public static Tag toTag(@Nullable net.minecraft.nbt.Tag handle) {
        return switch (handle) {
            case null -> null;
            case CompoundTag compoundTag -> new CompoundTagImpl(compoundTag);
            case ListTag tags -> new ListTagImpl(tags);
            case StringTag stringTag -> new StringTagImpl(stringTag);
            default -> new TagImpl<>(handle);
        };
    }

    @Override
    public @NotNull String asString() {
        return handle.getAsString(); // asString
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
        if (!(obj instanceof Tag) && !(obj instanceof net.minecraft.nbt.Tag)) {
            return false;
        }
        if (obj instanceof Tag) {
            obj = ((TagImpl<?>) obj).getHandle();
        }
        return handle.equals(obj);
    }
}
