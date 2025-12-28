plugins {
    java
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "9.3.0"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19" apply false
}

allprojects {
    group = "net.azisaba.loreeditor"
    version = "1.3.2"

    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
        plugin("com.gradleup.shadow")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(8))

        withSourcesJar()
        withJavadocJar()
    }

    publishing {
        repositories {
            maven {
                name = "repo"
                credentials(PasswordCredentials::class)
                url = uri(
                    if (project.version.toString().endsWith("SNAPSHOT"))
                        project.findProperty("deploySnapshotURL") ?: System.getProperty("deploySnapshotURL", "https://repo.azisaba.net/repository/maven-snapshots/")
                    else
                        project.findProperty("deployReleasesURL") ?: System.getProperty("deployReleasesURL", "https://repo.azisaba.net/repository/maven-releases/")
                )
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/public/") }
        maven("https://repo.papermc.io/repository/maven-public/") {
            content {
                includeGroup("io.papermc.paper")
            }
        }
        maven { url = uri("https://repo.azisaba.net/repository/maven-public/") }
        maven { url = uri("https://libraries.minecraft.net/") }
        if (properties["azisabaNmsUsername"] != null && properties["azisabaNmsPassword"] != null) {
            maven {
                name = "azisabaNms"
                credentials(PasswordCredentials::class)
                url = uri("https://repo.azisaba.net/repository/nms/")
            }
        }
    }

    tasks {
        javadoc {
            options.encoding = "UTF-8"
        }

        compileJava {
            options.encoding = "UTF-8"
        }

        shadowJar {
            relocate("xyz.acrylicstyle.util", "net.azisaba.loreeditor.libs.xyz.acrylicstyle.util")
            relocate("net.kyori", "net.azisaba.loreeditor.libs.net.kyori")
            relocate("com.google.gson", "net.azisaba.loreeditor.libs.com.google.gson")
            archiveBaseName.set("LoreEditor-${project.name}")
        }
    }
}
