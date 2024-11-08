package net.azisaba.loreeditor.common.network;

import com.google.gson.JsonParseException;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.azisaba.loreeditor.api.event.EventBus;
import net.azisaba.loreeditor.api.event.ItemEvent;
import net.azisaba.loreeditor.api.item.CraftItemStack;
import net.azisaba.loreeditor.api.item.ItemStack;
import net.azisaba.loreeditor.api.item.tag.CompoundTag;
import net.azisaba.loreeditor.api.item.tag.ListTag;
import net.azisaba.loreeditor.api.item.tag.StringTag;
import net.azisaba.loreeditor.common.chat.Component;
import net.azisaba.loreeditor.common.network.packet.ClientboundSetSlot;
import net.azisaba.loreeditor.common.network.packet.ClientboundWindowItems;
import net.azisaba.loreeditor.common.network.packet.ServerboundClickContainerSlot;
import net.azisaba.loreeditor.common.network.packet.ServerboundSetCreativeSlot;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.acrylicstyle.util.PerformanceCounter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PacketPreHandler extends ChannelDuplexHandler {
    private static final PerformanceCounter PROCESS_ITEM_PERF_COUNTER = new PerformanceCounter(PerformanceCounter.Unit.NANOSECONDS);
    private final Plugin plugin;
    private final Player player;

    public PacketPreHandler(@NotNull Plugin plugin, @NotNull Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public static @NotNull String getPerformanceStats(boolean multiline) {
        return PROCESS_ITEM_PERF_COUNTER.getDetails(multiline);
    }

    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
        // client -> server
        try {
            if (msg.getClass().getSimpleName().contains("SetCreative")) {
                ServerboundSetCreativeSlot packet = ServerboundSetCreativeSlot.getInstance(msg);
                reverseProcessItemStack(packet.getItem());
            } else if (msg.getClass().getSimpleName().contains("WindowClick") || msg.getClass().getSimpleName().contains("ContainerClick")) {
                ServerboundClickContainerSlot packet = ServerboundClickContainerSlot.getInstance(msg);
                reverseProcessItemStack(packet.getItem());
            } else if (msg.getClass().getSimpleName().contains("ContainerClose") || msg.getClass().getSimpleName().contains("WindowClose")) {
                if (player.getOpenInventory().getType() == InventoryType.MERCHANT) {
                    // re-add lore after trading
                    Bukkit.getScheduler().runTask(plugin, player::updateInventory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        // server -> client
        try {
            if (msg.getClass().getSimpleName().contains("WindowItems") || msg.getClass().getSimpleName().contains("ContainerSetContent")) {
                if (player.getOpenInventory().getType() != InventoryType.MERCHANT) {
                    ClientboundWindowItems packet = ClientboundWindowItems.getInstance(msg);
                    packet.setItems(packet.getItems().stream().map(ItemStack::copy).peek(i -> {
                        PROCESS_ITEM_PERF_COUNTER.recordStart();
                        try {
                            processItemStack(i);
                        } finally {
                            PROCESS_ITEM_PERF_COUNTER.recordEnd();
                        }
                    }).collect(Collectors.toList()));
                }
            } else if (msg.getClass().getSimpleName().contains("SetSlot")) {
                if (player.getOpenInventory().getType() != InventoryType.MERCHANT) {
                    ClientboundSetSlot packet = ClientboundSetSlot.getInstance(msg);
                    ItemStack item = packet.getItem();
                    if (item != null) {
                        item = item.copy();
                        PROCESS_ITEM_PERF_COUNTER.recordStart();
                        try {
                            processItemStack(item);
                        } finally {
                            PROCESS_ITEM_PERF_COUNTER.recordEnd();
                        }
                        packet.replaceItem(item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.write(ctx, msg, promise);
    }

    public void processItemStack(@Nullable ItemStack item) {
        if (item == null || item.getCount() == 0) return;
        CompoundTag tag = item.getTag();
        boolean hadTag = tag != null;
        if (tag == null) {
            tag = CompoundTag.getInstance(null).constructor();
        }
        if (tag.hasKeyOfType("lore_editor", 10)) {
            return;
        }
        CompoundTag loreEditorTag = CompoundTag.getInstance(null).constructor();
        AtomicReference<CompoundTag> displayTag = new AtomicReference<>(tag.getCompound("display"));
        AtomicInteger lines = new AtomicInteger();
        boolean hadDisplayTag = tag.hasKeyOfType("display", 10);
        boolean hadLoreTag = false;
        List<String> components =
                EventBus.INSTANCE.callEvent(new ItemEvent(player, CraftItemStack.STATIC.asCraftMirror(item), tag))
                        .getComponents()
                        .stream()
                        .map(component -> JSONComponentSerializer.json().serialize(component))
                        .collect(Collectors.toList());
        if (components.isEmpty()) return;
        Consumer<ListTag> addLore = list -> {
            for (int i = 0; i < components.size(); i++) {
                String component = components.get(i);
                if (component == null) {
                    plugin.getLogger().warning("Attempted to add null component at #" + i);
                    continue;
                }
                list.add(StringTag.create(component));
            }
            lines.addAndGet(components.size());
            displayTag.get().set("Lore", list);
        };
        if (displayTag.get().hasKeyOfType("Lore", 8) || displayTag.get().hasKeyOfType("Lore", 9)) {
            hadLoreTag = true;
            if (displayTag.get().hasKeyOfType("Lore", 8)) {
                // string
                try {
                    Component component = Component.STATIC.deserialize(displayTag.get().getString("Lore"));
                    if (component != null) {
                        for (int i = 0; i < components.size(); i++) {
                            String s = components.get(i);
                            if (s == null) {
                                plugin.getLogger().warning("Attempted to add null component at #" + i);
                                continue;
                            }
                            component.addSiblingText(LegacyComponentSerializer.legacySection().serialize(JSONComponentSerializer.json().deserialize(s)));
                        }
                        lines.addAndGet(components.size());
                        displayTag.get().setString("Lore", Component.STATIC.serialize(component));
                        tag.set("display", displayTag.get());
                    }
                } catch (JsonParseException ignore) {
                    // ignore
                }
            } else {
                // list
                ListTag list = displayTag.get().getList("Lore", 8);
                addLore.accept(list);
                tag.set("display", displayTag.get());
            }
        } else {
            ListTag list = ListTag.getInstance(null).constructor();
            addLore.accept(list);
            tag.set("display", displayTag.get());
        }
        if (lines.get() >= 1) {
            loreEditorTag.setInt("modify_count", lines.get());
        } else {
            return;
        }
        loreEditorTag.setBoolean("had_display_tag", hadDisplayTag);
        loreEditorTag.setBoolean("had_lore_tag", hadLoreTag);
        loreEditorTag.setBoolean("had_tag", hadTag);
        if (lines.get() == 0 && !hadTag) {
            return;
        }
        tag.set("lore_editor", loreEditorTag);
        item.setTag(tag);
    }

    public void reverseProcessItemStack(@Nullable ItemStack item) {
        if (item == null) return;
        CompoundTag tag = item.getTag();
        if (tag == null) return;
        if (!tag.hasKeyOfType("lore_editor", 10)) {
            return;
        }
        CompoundTag loreEditorTag = tag.getCompound("lore_editor");
        boolean hadTag = loreEditorTag.getBoolean("had_tag");
        if (!hadTag) {
            item.setTag(null);
            return;
        }
        Runnable removeTags = () -> {
            tag.remove("lore_editor");
            item.setTag(tag);
        };
        boolean hadDisplayTag = loreEditorTag.getBoolean("had_display_tag");
        if (!hadDisplayTag) {
            tag.remove("display");
            removeTags.run();
            return;
        }
        boolean hadLoreTag = loreEditorTag.getBoolean("had_lore_tag");
        int count = loreEditorTag.getInt("modify_count");
        CompoundTag displayTag = tag.getCompound("display");
        if (!hadLoreTag) {
            displayTag.remove("Lore");
            tag.set("display", displayTag);
            removeTags.run();
            return;
        }
        if (displayTag.hasKeyOfType("Lore", 8) || displayTag.hasKeyOfType("Lore", 9)) {
            if (displayTag.hasKeyOfType("Lore", 8)) {
                try {
                    Component component = Component.STATIC.deserialize(displayTag.getString("Lore"));
                    if (component != null) {
                        for (int i = 0; i < count; i++) {
                            component.getSiblings().remove(component.getSiblings().size() - 1);
                        }
                        displayTag.setString("Lore", Component.STATIC.serialize(component));
                        tag.set("display", displayTag);
                    }
                } catch (JsonParseException ignored) {
                }
            } else {
                ListTag list = displayTag.getList("Lore", 8);
                for (int i = 0; i < count; i++) {
                    list.removeAt(list.size() - 1);
                }
                displayTag.set("Lore", list);
                tag.set("display", displayTag);
            }
        }
        removeTags.run();
    }
}
