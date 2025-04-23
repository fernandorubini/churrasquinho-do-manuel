package com.example.grupochurrasquinhodomanuel.features.customer.ui.cart

import androidx.lifecycle.ViewModel
import com.example.grupochurrasquinhodomanuel.core.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addItem(item: CartItem) {
        val currentItems = _cartItems.value.toMutableList()
        val existing = currentItems.find { it.id == item.id }
        if (existing != null) {
            existing.quantity += item.quantity
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

    fun totalPrice(): Double {
        return _cartItems.value.sumOf { it.price * it.quantity }
    }
}