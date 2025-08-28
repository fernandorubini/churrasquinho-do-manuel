package com.example.grupochurrasquinhodomanuel.features.brand.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.data.repository.BrandRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BrandViewModel(
    private val repository: BrandRepository
) : ViewModel() {

    val brands: StateFlow<List<Brand>> = repository.getAllBrands()
        .map { it.sortedBy { brand -> brand.name } } // Exemplo de melhoria de UX
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addBrand(brand: Brand) {
        viewModelScope.launch {
            repository.insert(brand)
        }
    }
}
