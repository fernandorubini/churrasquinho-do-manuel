package com.example.grupochurrasquinhodomanuel.features.login.model

import com.example.grupochurrasquinhodomanuel.UserType

sealed class LoginState {
    object Idle : LoginState() // Estado inicial (não fazendo nada)
    object Loading : LoginState() // Estado de carregamento enquanto o login está sendo feito
    data class Success(val message: String, val userType: UserType) : LoginState() // Login bem-sucedido
    data class Error(val message: String) : LoginState() // Erro de login
}
