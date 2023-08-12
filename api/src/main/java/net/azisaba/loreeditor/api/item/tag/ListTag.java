package net.azisaba.loreeditor.api.item.tag;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ListTag extends Tag {
    static @NotNull ListTag getInstance(@Nullable Object o) {
        return (ListTag) ReflectionUtil.getImplInstance("item.tag.ListTag", o);
    }

    /**
     * Creates a new instance of the list tag. (static method)
     * @return the new tag
     */
    @NotNull
    ListTag constructor();

    /**
     * Returns the size of the list.
     * @return the size
     */
    int size();

    /**
     * Removes the element at the specified position in this list.
     * @param index index of the element to be removed
     */
    @NotNull
    Tag removeAt(int index);

    /**
     * Add the tag to the list.
     * @param index the index
     * @param tag the tag
     */
    void add(int index, @NotNull Tag tag);

    default void add(@NotNull Tag tag) {
        add(size(), tag);
    }
}
