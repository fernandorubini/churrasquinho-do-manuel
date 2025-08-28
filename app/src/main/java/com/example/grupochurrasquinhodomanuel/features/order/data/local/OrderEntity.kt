package com.example.grupochurrasquinhodomanuel.features.order.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.grupochurrasquinhodomanuel.core.database.Converters
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus
import java.math.BigDecimal

@Entity(tableName = "orders")
@TypeConverters(Converters::class)  // Usando o conversor
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerId: String,
    val status: OrderStatus,
    val date: String,  // Ex: "2025-06-18"
    val deliveryTime: Int? = null,  // em minutos
    val items: List<OrderItem>,  // Lista de OrderItem
    val total: BigDecimal
)
