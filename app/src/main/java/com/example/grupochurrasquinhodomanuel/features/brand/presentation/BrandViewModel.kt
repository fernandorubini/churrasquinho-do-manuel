package com.example.grupochurrasquinhodomanuel.features.brand.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.data.BrandRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BrandViewModel(
    private val repository: BrandRepository
) : ViewModel() {

    private val _brands = MutableStateFlow<List<Brand>>(emptyList())
    val brands: StateFlow<List<Brand>> = _brands

    init {
        loadBrands()
    }

    private fun loadBrands() {
        viewModelScope.launch {
            _brands.value = repository.getAllBrands()
        }
    }

    fun addBrand(brand: Brand) {
        viewModelScope.launch {
            repository.insert(brand)
            loadBrands()
        }
    }
}
