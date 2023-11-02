repositories {
    mavenLocal()
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

dependencies {
    compileOnly(project(":common"))
    compileOnly("org.spigotmc:spigot:1.20.2-R0.1-SNAPSHOT")
}
