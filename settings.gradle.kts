pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google() // Google's Maven Repository hinzufügen
        mavenCentral()
        // Fügen Sie hier weitere benötigte Repositories hinzu
    }
}

rootProject.name = "EP_2023_2024"
include(":app")
 