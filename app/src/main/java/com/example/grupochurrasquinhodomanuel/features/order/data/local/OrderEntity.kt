package com.example.grupochurrasquinhodomanuel.features.order.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.grupochurrasquinhodomanuel.core.database.Converters
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus
import java.math.BigDecimal

@Entity(tableName = "orders")
@TypeConverters(Converters::class)
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerId: String,
    val status: OrderStatus,
    val date: String,
    val deliveryTime: Int? = null,
    val items: List<OrderItem>,
    val total: BigDecimal
)
