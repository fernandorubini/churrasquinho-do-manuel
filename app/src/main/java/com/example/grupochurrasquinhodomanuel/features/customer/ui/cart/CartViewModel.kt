package com.example.grupochurrasquinhodomanuel.features.customer.ui.cart

import androidx.lifecycle.ViewModel
import com.example.grupochurrasquinhodomanuel.core.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addItem(item: CartItem) {
        val currentItems = _cartItems.value.toMutableList()
        val existingIndex = currentItems.indexOfFirst { it.id == item.id }

        if (existingIndex != -1) {
            val existing = currentItems[existingIndex]
            val updated = existing.copy(quantity = existing.quantity + item.quantity)
            currentItems[existingIndex] = updated
        } else {
            currentItems.add(item)
        }

        _cartItems.value = currentItems
    }


    fun removeItem(itemId: Int) {
        _cartItems.value = _cartItems.value.filterNot { it.id == itemId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun totalPrice(): BigDecimal {
        return _cartItems.value.fold(BigDecimal.ZERO) { acc, item ->
            acc + item.price.multiply(item.quantity.toBigDecimal())
        }
    }
}
