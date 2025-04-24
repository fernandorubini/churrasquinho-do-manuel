package com.example.grupochurrasquinhodomanuel.features.customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(private val repository: CustomerRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.getAllCustomers().collect {
                // lógica para lidar com os dados
            }
        }
    }

    fun doSomething() {
        // lógica da tela de customer
    }
}