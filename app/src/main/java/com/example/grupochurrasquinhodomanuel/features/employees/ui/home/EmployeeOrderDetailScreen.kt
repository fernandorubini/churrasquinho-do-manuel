package com.example.grupochurrasquinhodomanuel.features.employees.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.features.order.presentation.EmployeeOrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun EmployeeOrderDetailScreen(
    orderId: String,
    navController: NavController,
    viewModel: EmployeeOrderViewModel = koinViewModel()
) {
    // Coleta a lista do VM e filtra pelo id
    val orders by viewModel.orders.collectAsState(initial = emptyList())
    val order = remember(orders, orderId) { orders.firstOrNull { it.id.toString() == orderId } }

    val currencyFormatter = remember { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(Strings.Labels.ORDER_DETAILS) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            order?.let { current ->
                OrderDetailCard(
                    order = current,
                    onAdvanceStatus = { viewModel.advanceOrderStatus(context, current) },
                    currencyFormatter = currencyFormatter
                )
            } ?: CircularProgressIndicator()
        }
    }
}

@Composable
fun OrderDetailCard(
    order: OrderEntity,
    onAdvanceStatus: () -> Unit,
    currencyFormatter: NumberFormat,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID do Pedido: ${order.id}", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Cliente: ${order.customerId}")
            Text(text = "${Strings.Labels.STATUS}: ${order.status.getDisplayLabel()}")
            Text(text = "Data: ${order.date}")
            Text(text = "Total: ${currencyFormatter.format(order.total)}")

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Itens do Pedido:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))

            order.items.forEach { item ->
                // Cada item é OrderItem(product, quantity)
                Text("- ${item.product.name} (Qtd: ${item.quantity})")
            }

            if (order.status.name.lowercase() != "entregue") {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onAdvanceStatus,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(Strings.Buttons.NEXT_STATUS)
                }
            }
        }
    }
}
