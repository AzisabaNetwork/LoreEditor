plugins {
    id("io.papermc.paperweight.userdev")
}

repositories {
    mavenLocal()
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

dependencies {
    compileOnly(project(":common"))
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
}
