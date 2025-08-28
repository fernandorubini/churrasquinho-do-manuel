package com.example.grupochurrasquinhodomanuel.features.order.data.repository

import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderDao
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val dao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<OrderEntity>> =
        dao.getAll()

    override fun getOrderById(id: Long): Flow<OrderEntity?> =
        dao.getOrderById(id)

    override suspend fun getOrderByIdOnce(id: Long): OrderEntity? =
        dao.getOrderByIdOnce(id)

    override suspend fun updateOrder(order: OrderEntity) {
        dao.updateOrder(order)
    }
}
