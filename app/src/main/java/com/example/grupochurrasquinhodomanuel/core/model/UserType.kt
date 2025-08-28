package com.example.grupochurrasquinhodomanuel.core.model

enum class UserType {
    CUSTOMER,
    EMPLOYEE,
    MANAGEMENT;

    fun displayName(): String {
        return when (this) {
            CUSTOMER -> "Cliente"
            EMPLOYEE -> "Colaborador"
            MANAGEMENT -> "Gestão"
        }
    }

    companion object {
        fun fromString(value: String?): UserType {
            return runCatching { valueOf(value ?: "") }.getOrDefault(CUSTOMER)
        }
    }
}

