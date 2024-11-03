package net.azisaba.loreeditor.api.util;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class ReflectionUtil {
    public static boolean isModernNMS() {
        String v = getServerImplVersion();
        return !v.startsWith("v1_8_") &&
                !v.startsWith("v1_9_") &&
                !v.startsWith("v1_10_") &&
                !v.startsWith("v1_11_") &&
                !v.startsWith("v1_12_") &&
                !v.startsWith("v1_13_") &&
                !v.startsWith("v1_14_") &&
                !v.startsWith("v1_15_") &&
                !v.startsWith("v1_16_");
    }

    public static @NotNull String getServerImplVersion() {
        switch (Bukkit.getBukkitVersion()) {
            case "1.20.1-R0.1-SNAPSHOT":
            case "1.20.2-R0.1-SNAPSHOT":
                return "v1_20";
            case "1.21.1-R0.1-SNAPSHOT":
            case "1.21.2-R0.1-SNAPSHOT":
            case "1.21.3-R0.1-SNAPSHOT":
                return "v1_21_1";
        }
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static @NotNull Class<?> getNMSClass(@NotNull String name) {
        try {
            if (isModernNMS()) {
                return Class.forName(name);
            } else {
                return Class.forName("net.minecraft.server." + getServerImplVersion() + "." + name);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Class<?> getOBCClass(@NotNull String name) {
        try {
            return Class.forName(Bukkit.getServer().getClass().getPackage().getName() + "." + name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Class<?> getImplClass(@NotNull String relativeClassName) {
        try {
            return Class.forName("net.azisaba.loreeditor." + getServerImplVersion() + "." + relativeClassName + "Impl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Object newImplClassInstance(@NotNull String relativeClassName) {
        try {
            return Class.forName("net.azisaba.loreeditor." + getServerImplVersion() + "." + relativeClassName + "Impl")
                    .getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Object getImplInstance(@NotNull String relativeClassName, @Nullable Object o) {
        try {
            return getImplClass(relativeClassName).getMethod("getInstance", Object.class).invoke(null, o);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Object getField(@NotNull Object o, @NotNull String fieldName) {
        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(o);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
