package com.example.grupochurrasquinhodomanuel.core.model

import java.math.BigDecimal

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
)
