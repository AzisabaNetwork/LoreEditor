package net.azisaba.loreeditor.v1_20.item;

import net.azisaba.loreeditor.api.item.CraftItemStack;
import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.common.util.Reflected;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftItemStackImpl implements CraftItemStack {
    @Contract(value = "_ -> new", pure = true)
    @Reflected
    public static @NotNull CraftItemStackImpl getInstance(@Nullable Object item) {
        return new CraftItemStackImpl();
    }

    @Override
    public @Nullable ItemStack asNMSCopy(org.bukkit.inventory.@Nullable ItemStack item) {
        return new ItemStackImpl(org.bukkit.craftbukkit.v1_20_R2.inventory.CraftItemStack.asNMSCopy(item));
    }

    @NotNull
    @Override
    public org.bukkit.inventory.ItemStack asCraftMirror(@NotNull ItemStack item) {
        return org.bukkit.craftbukkit.v1_20_R2.inventory.CraftItemStack.asCraftMirror(((ItemStackImpl) item).handle());
    }
}
