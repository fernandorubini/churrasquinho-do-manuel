package com.example.grupochurrasquinhodomanuel.features.order.data

import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderDao
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.math.BigDecimal

class OrderTrackingRepository(
    private val dao: OrderDao
) {

    fun getAllOrders(): Flow<List<OrderEntity>> = dao.getAll()

    fun getOrder(orderId: String): Flow<OrderEntity?> {
        val idLong = orderId.toLongOrNull() ?: return flowOf(null)
        return dao.getOrderById(idLong)
    }

    fun getOrdersForCustomer(customerId: String): Flow<List<OrderEntity>> =
        dao.getOrdersForCustomer(customerId)


    suspend fun saveStatus(
        id: String,
        customerId: String,
        status: OrderStatus,
        date: String,
        items: List<OrderItem> = emptyList(),
        total: BigDecimal = BigDecimal.ZERO
    ) {
        val idLong = id.toLongOrNull() ?: return

        val current = dao.getOrderByIdOnce(idLong)
        if (current != null) {
            val updated = current.copy(
                status = status,
                date = date,
                items = items,
                total = total
            )
            dao.updateOrder(updated)
        } else {
            val newOrder = OrderEntity(
                id = id,
                customerId = customerId,
                status = status,
                date = date,
                items = items,
                total = total
            )
            dao.insertOrder(newOrder)
        }
    }
}
