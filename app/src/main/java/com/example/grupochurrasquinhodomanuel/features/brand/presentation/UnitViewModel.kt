package com.example.grupochurrasquinhodomanuel.features.brand.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.model.UnitEntity
import com.example.grupochurrasquinhodomanuel.data.repository.UnitRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UnitViewModel(
    private val repository: UnitRepository
) : ViewModel() {

    val units: StateFlow<List<UnitEntity>> = repository.getAllUnits()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addUnit(unit: UnitEntity) {
        viewModelScope.launch {
            repository.insert(unit)
        }
    }
}
