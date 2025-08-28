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

    /** Observa todos os pedidos (tempo real). */
    fun getAllOrders(): Flow<List<OrderEntity>> = dao.getAll()

    /** Observa um pedido específico (tempo real). */
    fun getOrder(orderId: String): Flow<OrderEntity?> {
        val idLong = orderId.toLongOrNull() ?: return flowOf(null)
        return dao.getOrderById(idLong)
    }

    /** Observa pedidos de um cliente (tempo real). */
    fun getOrdersForCustomer(customerId: String): Flow<List<OrderEntity>> =
        dao.getOrdersForCustomer(customerId)

    /**
     * Atualiza o status (e campos relevantes) do pedido.
     * Se existir: update; se não existir: insert.
     */
    suspend fun saveStatus(
        id: String,                   // <- id String (compatível com OrderEntity)
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
                id = id,                  // ✅ usa String, evitando o erro de tipo
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
