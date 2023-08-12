package net.azisaba.loreeditor.api.item.tag;

import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CompoundTag extends Tag {
    static @NotNull CompoundTag getInstance(@Nullable Object o) {
        return (CompoundTag) ReflectionUtil.getImplInstance("item.tag.CompoundTag", o);
    }

    @NotNull
    CompoundTag constructor();

    /**
     * Returns the size of the tag.
     * @return size
     */
    int size();

    /**
     * Checks if the key has a type of the given type.
     * @param key the key
     * @param type the type (99 stands for any number)
     * @return true if the key has a type of the given type; false otherwise
     */
    boolean hasKeyOfType(@NotNull String key, int type);

    /**
     * Removes the key from the tag.
     * @param key the key
     */
    void remove(@NotNull String key);

    /**
     * Returns the boolean value of the key.
     * @param key the key
     * @return boolean
     */
    boolean getBoolean(@NotNull String key);

    /**
     * Returns the int value of the key.
     * @param key the key
     * @return integer
     */
    int getInt(@NotNull String key);

    /**
     * Returns the string value of the key.
     * @param key the key
     * @return string
     */
    @NotNull
    String getString(@NotNull String key);

    /**
     * Returns the compound tag value of the key.
     * @param key the key
     * @return the tag
     */
    @NotNull
    CompoundTag getCompound(@NotNull String key);

    /**
     * Returns the list tag value of the key.
     * @param key the key
     * @param type the type of the list element
     * @return the tag
     */
    @NotNull
    ListTag getList(@NotNull String key, int type);

    /**
     * Set the string value of the key.
     * @param key the key
     * @param value the tag value
     */
    void setString(@NotNull String key, @NotNull String value);

    /**
     * Set the boolean value of the key.
     * @param key the key
     * @param value the tag value
     */
    void setBoolean(@NotNull String key, boolean value);

    /**
     * Set the int value of the key.
     * @param key the key
     * @param value the tag value
     */
    void setInt(@NotNull String key, int value);

    /**
     * Set the value of the key.
     * @param key the key
     * @param value the tag value
     */
    void set(@NotNull String key, @NotNull Tag value);

    /**
     * Get the tag at the key.
     * @param key the key
     * @return the tag
     */
    @Nullable
    Tag get(@NotNull String key);
}
