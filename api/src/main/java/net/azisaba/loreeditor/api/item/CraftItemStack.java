package net.azisaba.loreeditor.api.item;

import com.google.common.hash.Hashing;
import net.azisaba.loreeditor.api.util.ReflectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public interface CraftItemStack {
    @NotNull CraftItemStack STATIC = getInstance(null);

    static @NotNull CraftItemStack getInstance(@Nullable org.bukkit.inventory.ItemStack item) {
        return (CraftItemStack) ReflectionUtil.getImplInstance("item.CraftItemStack", item);
    }

    @SuppressWarnings("UnstableApiUsage")
    static @NotNull String getItemHash(@NotNull ItemStack item) {
        String hashedTag = item.getTag() != null ? Hashing.sha512().hashString(item.getTag().toString(), StandardCharsets.UTF_16).toString() : null;
        return STATIC.asCraftMirror(item).getType().name() + ";" + hashedTag;
    }

    /**
     * Returns the NMS copy of the item stack. (static method)
     * @param item the bukkit item stack
     * @return the nms item stack or null if the provided item stack is invalid
     */
    @Nullable
    ItemStack asNMSCopy(@Nullable org.bukkit.inventory.ItemStack item);

    /**
     * Returns the bukkit mirror of the item stack. (static method)
     * @param item the nms item stack
     * @return the bukkit item stack
     */
    @NotNull
    org.bukkit.inventory.ItemStack asCraftMirror(@NotNull ItemStack item);
}
