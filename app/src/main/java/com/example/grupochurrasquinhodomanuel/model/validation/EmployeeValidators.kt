package com.example.grupochurrasquinhodomanuel.model.validation

import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.model.Employee
import java.util.Locale

/**
 * Validações de domínio para Employee (sem dependências de Android).
 * Agora usando mensagens do Strings.Messages (i18n).
 */
object EmployeeValidators {

    /** Campos de Employee para os quais emitimos erros. */
    enum class Field { NAME, EMAIL, ROLE, DEPARTMENT }

    /** Erro de validação com o campo e mensagem. */
    data class Error(val field: Field, val message: String)

    /** Opções de validação (ajuste conforme regras do negócio). */
    data class Options(
        val minNameLen: Int = 2,
        val minRoleLen: Int = 2,
        val requireDepartment: Boolean = false
    )

    // Regex simples e robusta para e-mail (sem Android).
    private val EMAIL_REGEX = Regex(
        pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        option = RegexOption.IGNORE_CASE
    )

    /** Valida formato de e-mail (sem Android). */
    fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email.trim())

    /**
     * Valida um Employee. Retorna lista de erros; vazia = válido.
     * Dica: chame com employee.normalized() antes de salvar.
     */
    fun validate(employee: Employee, options: Options = Options()): List<Error> {
        val e = employee.normalized()
        val errs = mutableListOf<Error>()

        // Nome
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

        // E-mail
        if (!isValidEmail(e.email)) {
            errs += Error(Field.EMAIL, Strings.Messages.INVALID_EMAIL)
        }

        // Função (role)
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

        // Departamento (condicional)
        if (options.requireDepartment && e.department.isBlank()) {
            errs += Error(Field.DEPARTMENT, Strings.Messages.DEPARTMENT_REQUIRED)
        }

        return errs
    }

    /** Atalho: true se não há erros. */
    fun isValid(employee: Employee, options: Options = Options()): Boolean =
        validate(employee, options).isEmpty()
}
