package com.example.grupochurrasquinhodomanuel.features.customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.preferences.SessionPreferences
import com.example.grupochurrasquinhodomanuel.model.Customer
import com.example.grupochurrasquinhodomanuel.data.repository.CustomerRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val repository: CustomerRepository,
    private val session: SessionPreferences
) : ViewModel() {

    val customer: StateFlow<Customer?> = session.loggedInEmail
        .flatMapLatest { email ->
            if (email != null) repository.getCustomerByEmail(email) else flowOf(null)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.insertCustomer(customer)
        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.updateCustomer(customer)
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.deleteCustomer(customer)
        }
    }

    fun getCustomerByEmail(email: String): Flow<Customer?> {
        return repository.getCustomerByEmail(email)
    }
}
