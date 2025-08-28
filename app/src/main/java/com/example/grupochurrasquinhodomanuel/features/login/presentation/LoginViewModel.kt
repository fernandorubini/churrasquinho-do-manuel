package com.example.grupochurrasquinhodomanuel.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.features.login.model.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

import com.example.grupochurrasquinhodomanuel.core.model.UserType as ModelUserType

class LoginViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                val resolvedEmail = result.user?.email.orEmpty()
                val userType = getUserTypeFromEmail(resolvedEmail)

                _loginState.value = LoginState.Success(
                    message = "Login bem-sucedido",
                    userType = userType
                )
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(
                    message = e.message ?: "Erro desconhecido"
                )
            }
        }
    }

    private val EMPLOYEE_DOMAINS = setOf(
        "@churrasquinhodomanuel.com.br",
        "@ossukhikai.com.br",
        "@aielloi.com.br",
        "@buffalosred.com.br"
    )

    private fun getUserTypeFromEmail(email: String): ModelUserType {
        val e = email.lowercase(Locale.ROOT)
        return if (EMPLOYEE_DOMAINS.any { e.endsWith(it) }) {
            ModelUserType.EMPLOYEE
        } else {
            ModelUserType.CUSTOMER
        }
    }
}
