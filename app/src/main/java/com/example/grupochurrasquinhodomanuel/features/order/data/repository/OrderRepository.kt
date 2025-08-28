package com.example.grupochurrasquinhodomanuel.features.order.data.repository

import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<OrderEntity>>

    fun getOrderById(id: Long): Flow<OrderEntity?>

    suspend fun getOrderByIdOnce(id: Long): OrderEntity?

    suspend fun updateOrder(order: OrderEntity)
}
