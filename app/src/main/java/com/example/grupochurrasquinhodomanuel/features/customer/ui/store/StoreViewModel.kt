package com.example.grupochurrasquinhodomanuel.features.customer.presentation.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreViewModel(
    private val repository: ReviewRepository
) : ViewModel() {

    private val _welcomeMessage = MutableStateFlow("Bem-vindo, cliente!")
    val welcomeMessage: StateFlow<String> = _welcomeMessage

    init {
        viewModelScope.launch {
            // Futuro: carregar reviews, promoções, etc.
        }
    }

    fun updateWelcomeMessage(message: String) {
        _welcomeMessage.value = message
    }
}
