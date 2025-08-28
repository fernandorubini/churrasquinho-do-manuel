package com.example.grupochurrasquinhodomanuel.features.management.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.local.EmployeeEntity
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class EmployeeUiState(
    val loading: Boolean = true,
    val items: List<EmployeeEntity> = emptyList(),
    val error: String? = null
)

class EmployeeViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(EmployeeUiState())
    val ui: StateFlow<EmployeeUiState> = _ui

    init {
        viewModelScope.launch {
            repository.employees.collectLatest { list ->
                _ui.value = _ui.value.copy(loading = false, items = list, error = null)
            }
        }
    }

    fun toggleActive(id: String) {
        viewModelScope.launch {
            runCatching { repository.toggleActive(id) }
                .onFailure { e -> _ui.value = _ui.value.copy(error = e.message) }
        }
    }

    fun upsert(employee: EmployeeEntity) {
        viewModelScope.launch {
            runCatching { repository.upsert(employee) }
                .onFailure { e -> _ui.value = _ui.value.copy(error = e.message) }
        }
    }

    fun delete(employee: EmployeeEntity) {
        viewModelScope.launch {
            runCatching { repository.delete(employee) }
                .onFailure { e -> _ui.value = _ui.value.copy(error = e.message) }
        }
    }
}
