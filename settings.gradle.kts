pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // ✅ Alterado
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GrupoChurrasquinhoDoManuel"
include(":app")
