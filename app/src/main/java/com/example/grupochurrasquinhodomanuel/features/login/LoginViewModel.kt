package com.example.grupochurrasquinhodomanuel.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.UserType
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // Função de login, retornando sucesso ou erro através de flow
    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            try {
                // Usando await() para aguardar a conclusão da tarefa assíncrona
                val result = auth.signInWithEmailAndPassword(email, password).await()
                _loginState.value = LoginState.Success("Login bem-sucedido", getUserTypeFromEmail(result.user?.email ?: ""))
            } catch (exception: Exception) {
                _loginState.value = LoginState.Error(exception.message ?: "Erro desconhecido")
            }
        }
    }

    // Função para identificar o tipo de usuário com base no e-mail
    private fun getUserTypeFromEmail(email: String): UserType {
        return when {
            email.endsWith("@churraquinhodomanuel.com.br") -> UserType.CLIENTE
            email.endsWith("@sushikai.com.br") -> UserType.COLABORADOR
            email.endsWith("@aieoli.com.br") -> UserType.GESTAO
            email.endsWith("@buffalosred.com.br") -> UserType.COLABORADOR
            else -> UserType.CLIENTE // Default caso não seja nenhum dos domínios conhecidos
        }
    }
}
