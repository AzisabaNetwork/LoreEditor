dependencies {
    api(project(":common"))
    api(project(":v1_15_R1"))
    api(project(":v1_16_R3"))
    api(project(":v1_19_R3"))
    api(project(":v1_20"))
    api(project(":v1_21_1"))
    api(project(":v1_21_3"))
}

tasks {
    processResources {
        // replace version
        filesMatching("plugin.yml") {
            filter(org.apache.tools.ant.filters.ReplaceTokens::class, mapOf("tokens" to mapOf("version" to project.version)))
        }
    }
}
