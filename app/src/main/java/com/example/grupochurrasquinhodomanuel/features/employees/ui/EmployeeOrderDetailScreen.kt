package com.example.grupochurrasquinhodomanuel.features.employees.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import com.example.grupochurrasquinhodomanuel.features.order.presentation.EmployeeOrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmployeeOrderDetailScreen(
    navController: NavController,
    orderId: Int,
    viewModel: EmployeeOrderViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val orderState by viewModel.order.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.loadOrderById(orderId)
    }

    orderState?.let { order ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Detalhes do Pedido") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text("Status: ${order.status.getDisplayLabel()}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Produtos:", fontWeight = FontWeight.Bold)
                LazyColumn {
                    items(order.items) { item ->
                        ProductItemRow(orderItem = item)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Total: R$ ${order.total}", fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.advanceOrderStatus(context, order)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Avançar Status")
                }
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ProductItemRow(orderItem: OrderItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(orderItem.product.name, fontWeight = FontWeight.Medium)
            Text("Qtd: ${orderItem.quantity}", style = MaterialTheme.typography.bodySmall)
        }
        Text("R$ ${orderItem.product.price}", style = MaterialTheme.typography.bodyMedium)
    }
}
