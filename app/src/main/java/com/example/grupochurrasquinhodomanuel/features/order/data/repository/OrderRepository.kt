package com.example.grupochurrasquinhodomanuel.features.order.data.repository

import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    /** Observa todos os pedidos em tempo real. */
    fun getAllOrders(): Flow<List<OrderEntity>>

    /** Observa um pedido específico em tempo real. */
    fun getOrderById(id: Long): Flow<OrderEntity?>

    /** Busca um pedido específico uma única vez (sem Flow). */
    suspend fun getOrderByIdOnce(id: Long): OrderEntity?

    /** Atualiza um pedido existente. */
    suspend fun updateOrder(order: OrderEntity)
}
