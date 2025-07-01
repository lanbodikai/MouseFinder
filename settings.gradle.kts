// settings.gradle.kts

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "MouseFinder"

// include takes vararg of project paths in Kotlin DSL:
include("app")
