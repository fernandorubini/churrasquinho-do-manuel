package com.example.grupochurrasquinhodomanuel.features.order.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    // Lista todos os pedidos (tempo real)
    @Query("SELECT * FROM orders ORDER BY date DESC")
    fun getAll(): Flow<List<OrderEntity>>

    // Lista pedidos de um cliente (tempo real)
    @Query("SELECT * FROM orders WHERE customerId = :customerId ORDER BY date DESC")
    fun getOrdersForCustomer(customerId: String): Flow<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE customerId = :customerId")
    suspend fun getOrdersByCustomer(customerId: String): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE id = :orderId LIMIT 1")
    suspend fun getOrderByIdOnce(orderId: Long): OrderEntity?

    @Query("SELECT * FROM orders WHERE id = :orderId LIMIT 1")
    fun getOrderById(orderId: Long): Flow<OrderEntity?>

    @Update
    suspend fun updateOrder(order: OrderEntity)

    @Query("DELETE FROM orders")
    suspend fun clearOrders()
}
