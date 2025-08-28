package com.example.grupochurrasquinhodomanuel.features.order.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.features.order.data.repository.OrderRepository
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus
import com.example.grupochurrasquinhodomanuel.core.notification.NotificationHelper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EmployeeOrderViewModel(
    private val repository: OrderRepository
) : ViewModel() {

    private val _order = MutableStateFlow<OrderEntity?>(null)
    val order: StateFlow<OrderEntity?> = _order.asStateFlow()

    val orders: Flow<List<OrderEntity>> = repository.getAllOrders()

    fun loadOrderById(orderId: Int) {
        viewModelScope.launch {
            repository.getOrderById(orderId.toLong())
                .collect { orderResult ->
                    _order.value = orderResult
                }
        }
    }

    fun advanceOrderStatus(context: Context, order: OrderEntity) {
        viewModelScope.launch {
            val nextStatus = order.status.nextStatus()
            if (nextStatus != null) {
                val updatedOrder = order.copy(status = nextStatus)
                repository.updateOrder(updatedOrder)
                _order.value = updatedOrder

                if (nextStatus == OrderStatus.A_CAMINHO) {
                    NotificationHelper.showNotification(
                        context = context,
                        title = "Pedido #${order.id} a caminho!",
                        message = "O pedido está a caminho da entrega."
                    )
                }

            }
        }
    }
}
