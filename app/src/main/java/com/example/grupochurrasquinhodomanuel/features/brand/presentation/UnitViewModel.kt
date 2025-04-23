package com.example.grupochurrasquinhodomanuel.features.unit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.UnitEntity
import com.example.grupochurrasquinhodomanuel.data.UnitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UnitViewModel(
    private val repository: UnitRepository
) : ViewModel() {

    private val _units = MutableStateFlow<List<UnitEntity>>(emptyList())
    val units: StateFlow<List<UnitEntity>> = _units

    init {
        loadUnits()
    }

    private fun loadUnits() {
        viewModelScope.launch {
            _units.value = repository.getAllUnits()
        }
    }

    fun addUnit(unit: UnitEntity) {
        viewModelScope.launch {
            repository.insert(unit)
            loadUnits()
        }
    }
}
