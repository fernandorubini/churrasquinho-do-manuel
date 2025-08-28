package com.example.grupochurrasquinhodomanuel.features.customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.core.preferences.FavoritePreferences
import com.example.grupochurrasquinhodomanuel.features.order.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FavoriteViewModel(
    private val preferences: FavoritePreferences
) : ViewModel() {

    private val allProducts: List<Product> = listOf(
        Product(
            id = 1,
            name = Strings.Labels.PRODUCT_CHURRASCO_MISTO,
            description = Strings.Labels.PRODUCT_CHURRASCO_MISTO_DESC,
            price = BigDecimal("39.90"),
            imageUrl = "",
            quantity = 1,
            storeName = Strings.Labels.STORE_CHURRASQUINHO
        ),
        Product(
            id = 2,
            name = Strings.Labels.PRODUCT_GUARANA,
            description = Strings.Labels.PRODUCT_GUARANA_DESC,
            price = BigDecimal("8.00"),
            imageUrl = "",
            quantity = 1,
            storeName = Strings.Labels.STORE_AI_OLI
        ),
        Product(
            id = 3,
            name = Strings.Labels.PRODUCT_SANDWICHS,
            description = Strings.Labels.PRODUCT_SANDWICHS_DESC,
            price = BigDecimal("5.00"),
            imageUrl = "",
            quantity = 1,
            storeName = Strings.Labels.STORE_BUFFALOS_RED
        )
    )

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        viewModelScope.launch {
            preferences.favoriteIds.collect { ids ->
                val favorites = allProducts.map { product ->
                    product.copy(isFavorite = product.id.toString() in ids)
                }
                _products.value = favorites
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val id = product.id.toString()
            if (product.isFavorite) {
                preferences.remove(id)
            } else {
                preferences.add(id)
            }
        }
    }
}
