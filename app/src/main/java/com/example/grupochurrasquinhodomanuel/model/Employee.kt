package com.example.grupochurrasquinhodomanuel.model

data class Employee(
    val id: Long = 0L,
    val name: String = "",
    val email: String = "",
    val role: String = "",
    val department: String = ""
) {
    /**
     * Retorna uma cópia com strings aparadas (útil antes de salvar).
     */
    fun normalized(): Employee = copy(
        name = name.trim(),
        email = email.trim(),
        role = role.trim(),
        department = department.trim()
    )

    companion object {
        /** Instância vazia padrão. */
        val EMPTY: Employee = Employee()
    }
}
