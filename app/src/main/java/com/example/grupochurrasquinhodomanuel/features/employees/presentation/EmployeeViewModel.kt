package com.example.grupochurrasquinhodomanuel.features.employees.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.model.Employee

class EmployeeViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    // Agora o ViewModel expõe a LISTA de domínio (não Entity)
    val employees: StateFlow<List<Employee
            >> =
        repository.getAllEmployees() // Flow<List<Employees>>
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = emptyList()
            )

    // Adicionar funcionário usando o modelo de domínio
    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            repository.insertEmployee(employee)
        }
    }
}
