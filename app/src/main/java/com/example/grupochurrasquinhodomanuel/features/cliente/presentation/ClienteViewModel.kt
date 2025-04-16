package com.example.grupochurrasquinhodomanuel.features.cliente.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.AvaliacaoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val repository: AvaliacaoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow("Bem-vindo, cliente!")
    val uiState: StateFlow<String> = _uiState

    init {
        // Exemplo de uso inicial: você pode carregar dados do banco aqui
        viewModelScope.launch {
            // Aqui você pode buscar dados do repositório se quiser
        }
    }

    fun atualizarMensagemNova(mensagem: String) {
        _uiState.value = mensagem
    }
}