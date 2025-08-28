package com.example.grupochurrasquinhodomanuel.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.UserType
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Messages.ERROR_LOGGING
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Messages.ERROR_REGISTERING
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Messages.USER_TYPE_NOT_FOUND
import com.example.grupochurrasquinhodomanuel.core.firebase.FirebaseAuthManager
import com.example.grupochurrasquinhodomanuel.core.preferences.AuthPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userType: UserType) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(email: String, password: String, userType: UserType) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            firebaseAuthManager.register(email, password)
                .onSuccess {
                    authPreferences.saveUserEmail(email)
                    authPreferences.saveUserType(userType)
                    _authState.value = AuthState.Success(userType)
                }
                .onFailure { e ->
                    _authState.value = AuthState.Error(e.message ?: ERROR_REGISTERING)
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            firebaseAuthManager.login(email, password)
                .onSuccess {
                    val userType = authPreferences.getUserType()
                    if (userType != null) {
                        authPreferences.saveUserEmail(email)
                        _authState.value = AuthState.Success(userType)
                    } else {
                        _authState.value = AuthState.Error(USER_TYPE_NOT_FOUND)
                    }
                }
                .onFailure { e ->
                    _authState.value = AuthState.Error(e.message ?: ERROR_LOGGING)
                }
        }
    }

    fun getCurrentUserEmail(): String? {
        return firebaseAuthManager.getCurrentUserEmail()
    }

    fun logout() {
        firebaseAuthManager.logout()
        _authState.value = AuthState.Idle
    }
}
