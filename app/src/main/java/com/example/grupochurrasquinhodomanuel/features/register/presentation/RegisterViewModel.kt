package com.example.grupochurrasquinhodomanuel.features.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.UserType
import com.example.grupochurrasquinhodomanuel.core.firebase.FirebaseAuthManager
import com.example.grupochurrasquinhodomanuel.core.preferences.AuthPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Boolean get() = _isLoading.value

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    fun register(email: String, password: String, confirmPassword: String, onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank() || password != confirmPassword) {
            _errorMessage.value = "Preencha corretamente os campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            firebaseAuthManager.register(email, password)
                .onSuccess {
                    authPreferences.saveUserEmail(email)
                    authPreferences.saveUserType(UserType.CUSTOMER)
                    _errorMessage.value = null
                    _isLoading.value = false
                    onSuccess()
                }
                .onFailure { e ->
                    _errorMessage.value = e.message ?: "Erro ao registrar"
                    _isLoading.value = false
                }
        }
    }
}
