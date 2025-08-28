package com.example.grupochurrasquinhodomanuel.model.validation

import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.model.Employee
import java.util.Locale

object EmployeeValidators {

    enum class Field { NAME, EMAIL, ROLE, DEPARTMENT }

    data class Error(val field: Field, val message: String)

    data class Options(
        val minNameLen: Int = 2,
        val minRoleLen: Int = 2,
        val requireDepartment: Boolean = false
    )

    private val EMAIL_REGEX = Regex(
        pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        option = RegexOption.IGNORE_CASE
    )

    fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email.trim())


    fun validate(employee: Employee, options: Options = Options()): List<Error> {
        val e = employee.normalized()
        val errs = mutableListOf<Error>()

        if (e.name.length < options.minNameLen) {
            errs += Error(
                Field.NAME,
                String.format(
                    Locale.ROOT,
                    Strings.Messages.NAME_TOO_SHORT,
                    options.minNameLen
                )
            )
        }

        if (!isValidEmail(e.email)) {
            errs += Error(Field.EMAIL, Strings.Messages.INVALID_EMAIL)
        }

        if (e.role.length < options.minRoleLen) {
            errs += Error(
                Field.ROLE,
                String.format(
                    Locale.ROOT,
                    Strings.Messages.ROLE_TOO_SHORT,
                    options.minRoleLen
                )
            )
        }

        if (options.requireDepartment && e.department.isBlank()) {
            errs += Error(Field.DEPARTMENT, Strings.Messages.DEPARTMENT_REQUIRED)
        }

        return errs
    }

    fun isValid(employee: Employee, options: Options = Options()): Boolean =
        validate(employee, options).isEmpty()
}
