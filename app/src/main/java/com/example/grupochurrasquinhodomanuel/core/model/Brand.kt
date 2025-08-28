package com.example.grupochurrasquinhodomanuel.core.model

/**
 * Enum de marcas usadas no app.
 * Usamos [displayName] para persistência e exibição.
 */
enum class Brand(val displayName: String) {
    CHURRASQUINHO("Churrasquinho do Manuel"),
    BUFFALOS_RED("Buffalos Red"),
    AI_OLI("Ai & Oli");

    companion object {
        /** Encontra a Brand pelo displayName salvo no banco. */
        fun fromDisplayName(name: String): Brand? =
            // Se seu Kotlin não tiver `entries`, troque por: values().firstOrNull { ... }
            entries.firstOrNull { it.displayName == name }
    }
}
