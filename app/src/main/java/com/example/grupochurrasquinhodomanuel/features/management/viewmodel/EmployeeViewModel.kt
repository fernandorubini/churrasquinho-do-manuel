package com.example.grupochurrasquinhodomanuel.features.management.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    val employeesState: StateFlow<List<Employee>> =
        repository.getAllEmployees()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun insert(employee: Employee) = viewModelScope.launch {
        repository.insertEmployee(employee)
    }

    fun update(id: Long, employee: Employee) = viewModelScope.launch {
        repository.updateEmployeeById(id, employee)
    }

    fun toggleActive(id: Long) = viewModelScope.launch {
        repository.toggleActive(id)
    }

    fun delete(id: Long) = viewModelScope.launch {
        repository.deleteEmployeeById(id)
    }

    fun delete(employee: Employee) = viewModelScope.launch {
        repository.deleteEmployee(employee)
    }
}
