# LoreEditor

Packet-level item lore modification for Spigot and its forks.

## ⚠️Warning

This plugin only supports versions listed in the [Modrinth page](https://modrinth.com/plugin/loreeditor). Other versions will NOT work.

Check your server version before downloading the plugin!

## ✨Features

- Add lore lines to items, without affecting an actual item.

## 📥Installation

You can download the latest version of LoreEditor from the [Modrinth](https://modrinth.com/plugin/loreeditor) and then place it in your `plugins` folder.

## 🔧Developer API

LoreEditor provides a simple API for developers to use.

### 💻Dependency

#### 📝plugin.yml

To use LoreEditor in your plugin, you need to add the following to your `plugin.yml`:

```yaml
depend: [LoreEditor]
```

#### Maven

If you are using Maven, add the following to your `<repositories>` tag in `pom.xml`:

```xml
<repository>
    <id>azisaba</id>
    <url>https://repo.azisaba.net/repository/maven-public/</url>
</repository>
```

And add the following to your `<dependencies>` tag in `pom.xml`:

```xml
<dependency>
    <groupId>net.azisaba.loreeditor</groupId>
    <artifactId>api</artifactId>
    <version>[version]</version>
    <scope>provided</scope>
    <classifier>all</classifier> <!-- don't forget "all" classifier -->
</dependency>
```

#### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven("https://repo.azisaba.net/repository/maven-public/")
}

dependencies {
    compileOnly("net.azisaba.loreeditor:api:[version]:all") // don't forget "all" classifier
}
```

### ⌨️Usage

#### Add lore lines

```java
import net.azisaba.loreeditor.api.event.EventBus;
import net.azisaba.loreeditor.api.event.ItemEvent;
import net.azisaba.loreeditor.libs.net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // In LoreEditor, we use EventBus to listen for events.
        // This example adds "Hello, world!" to the lore of the every item.
        // Please note that blocking operations (such as file IO and database operations) should not be performed in the event listener.
        EventBus.INSTANCE.register(this, ItemEvent.class, 0, e -> {
            e.addLore(Component.text("Hello, world!"));
            // more lore lines...
        });
    }

    // we don't need to unregister the listener because LoreEditor will handle it.
}
```

## 📜License

LoreEditor is licensed under the [GNU General Public License v3.0](LICENSE).
