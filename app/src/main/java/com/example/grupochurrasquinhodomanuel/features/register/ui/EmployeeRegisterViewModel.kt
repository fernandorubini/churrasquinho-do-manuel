package com.example.grupochurrasquinhodomanuel.features.register.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.firebase.FirebaseAuthManager
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.launch

class EmployeeRegisterViewModel(
    private val repository: EmployeeRepository,
    private val auth: FirebaseAuthManager
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun isCorporateEmail(email: String): Boolean {
        val e = email.trim()
        return e.endsWith("@churrasquinhodomanuel.com.br") ||
                e.endsWith("@sushikai.com.br") ||
                e.endsWith("@aieoli.com.br") ||
                e.endsWith("@buffalosred.com.br")
    }

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit
    ) {
        val n = name.trim()
        val em = email.trim()
        val p = password.trim()
        val c = confirmPassword.trim()

        when {
            n.isEmpty() -> { errorMessage = "Nome obrigatório"; return }
            !isCorporateEmail(em) -> { errorMessage = "E-mail corporativo inválido"; return }
            p != c -> { errorMessage = "Senhas não coincidem"; return }
            p.length < 6 -> { errorMessage = "Senha mínima de 6 caracteres"; return }
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            auth.register(em, p)
                .onSuccess {
                    try {
                        val employee = Employee(
                            id = 0L,               // Room autogerará
                            name = n,
                            email = em,
                            role = "EMPLOYEE",
                            department = "GENERAL"
                        )
                        repository.insertEmployee(employee)
                        onSuccess()
                    } catch (e: Exception) {
                        errorMessage = "Erro ao salvar: ${e.localizedMessage}"
                    } finally {
                        isLoading = false
                    }
                }
                .onFailure { e ->
                    isLoading = false
                    errorMessage = e.message ?: "Erro desconhecido no registro"
                }
        }
    }
}
