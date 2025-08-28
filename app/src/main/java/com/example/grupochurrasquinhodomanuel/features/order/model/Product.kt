package com.example.grupochurrasquinhodomanuel.features.order.model

import java.io.Serializable
import java.math.BigDecimal

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String,
    val quantity: Int = 1,
    val isFavorite: Boolean = false,
    val storeName: String
) : Serializable {
    fun totalPrice(): BigDecimal = price.multiply(BigDecimal(quantity))
}
