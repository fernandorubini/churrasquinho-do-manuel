package com.example.grupochurrasquinhodomanuel.features.login.model

import com.example.grupochurrasquinhodomanuel.core.model.UserType

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val message: String, val userType: UserType) : LoginState()
    data class Error(val message: String) : LoginState()
}

