pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // Opcional, mas útil para plugins externos
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GrupoChurrasquinhoDoManuel"
include(":app")
