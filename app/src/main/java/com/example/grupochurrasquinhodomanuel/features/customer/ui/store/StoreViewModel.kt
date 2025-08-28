package com.example.grupochurrasquinhodomanuel.features.customer.ui.store

import androidx.lifecycle.ViewModel
import com.example.grupochurrasquinhodomanuel.data.repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoreViewModel(
    private val repository: ReviewRepository
) : ViewModel() {

    private val _welcomeMessage = MutableStateFlow("Bem-vindo, cliente!")
    val welcomeMessage: StateFlow<String> = _welcomeMessage

    fun updateWelcomeMessage(message: String) {
        _welcomeMessage.value = message
    }
}
