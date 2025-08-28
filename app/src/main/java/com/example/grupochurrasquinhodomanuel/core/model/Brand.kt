package com.example.grupochurrasquinhodomanuel.core.model


enum class Brand(val displayName: String) {
    CHURRASQUINHO("Churrasquinho do Manuel"),
    BUFFALOS_RED("Buffalos Red"),
    AI_OLI("Ai & Oli");

    companion object {
        fun fromDisplayName(name: String): Brand? =
            entries.firstOrNull { it.displayName == name }
    }
}
