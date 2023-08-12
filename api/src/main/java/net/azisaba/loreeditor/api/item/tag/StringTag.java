package net.azisaba.loreeditor.api.item.tag;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StringTag extends Tag {
    static @NotNull StringTag create(@NotNull String text) {
        return getInstance(null).valueOf(text);
    }

    static @NotNull StringTag getInstance(@Nullable Object o) {
        return (StringTag) ReflectionUtil.getImplInstance("item.tag.StringTag", o);
    }

    /**
     * Creates a new instance of the string tag. (static method)
     * @return the new tag
     */
    @NotNull
    StringTag valueOf(@NotNull String text);
}
