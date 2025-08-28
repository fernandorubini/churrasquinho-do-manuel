package com.example.grupochurrasquinhodomanuel.features.customer.ui.order.presentation

import androidx.lifecycle.ViewModel
import com.example.grupochurrasquinhodomanuel.features.order.data.OrderTrackingRepository
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderTrackingViewModel(
    private val repository: OrderTrackingRepository
) : ViewModel() {

    fun getOrderById(id: String): Flow<OrderEntity?> {
        return repository.getOrder(id)
    }
}
