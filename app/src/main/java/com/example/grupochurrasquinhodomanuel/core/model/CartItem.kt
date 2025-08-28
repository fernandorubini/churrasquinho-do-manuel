package com.example.grupochurrasquinhodomanuel.core.model

import java.math.BigDecimal

data class CartItem(
    val id: Int,
    val name: String,
    val price: BigDecimal,
    val imageUrl: String,
    val quantity: Int
)
