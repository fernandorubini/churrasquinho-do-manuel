package com.example.grupochurrasquinhodomanuel.model

data class Employee(
    val id: Long = 0L,
    val name: String = "",
    val email: String = "",
    val role: String = "",
    val department: String = ""
) {

    fun normalized(): Employee = copy(
        name = name.trim(),
        email = email.trim(),
        role = role.trim(),
        department = department.trim()
    )

    companion object {
        val EMPTY: Employee = Employee()
    }
}
