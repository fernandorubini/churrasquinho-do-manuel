package com.example.grupochurrasquinhodomanuel.features.customer.ui.orderhistory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.core.util.toCurrencyString
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderDao
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class OrderHistoryViewModel(
    private val dao: OrderDao
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrderEntity>>(emptyList())
    val orders: StateFlow<List<OrderEntity>> = _orders.asStateFlow()

    fun loadOrdersForCustomer(customerId: String) {
        viewModelScope.launch {
            dao.getOrdersForCustomer(customerId).collect { list ->
                _orders.value = list
            }
        }
    }
}

@Composable
fun OrderHistoryScreen(
    customerId: String,
    viewModel: OrderHistoryViewModel = koinViewModel()
) {
    LaunchedEffect(customerId) {
        viewModel.loadOrdersForCustomer(customerId)
    }

    val orders by viewModel.orders.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            items = orders
        ) { order: OrderEntity ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "${Strings.Labels.ORDER_DETAILS} #${order.id}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Data: ${order.date}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Status: ${order.status}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Itens:",
                        style = MaterialTheme.typography.bodySmall
                    )

                    order.items.forEach { item: OrderItem ->
                        Text(
                            text = "- ${item.product.name} x ${item.quantity}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Total: ${order.total.toCurrencyString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
