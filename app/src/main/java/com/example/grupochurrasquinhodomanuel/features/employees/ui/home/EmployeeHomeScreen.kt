package com.example.grupochurrasquinhodomanuel.features.employees.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.features.order.presentation.EmployeeOrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.*

@Composable
fun EmployeeHomeScreen(
    navController: NavController,
    viewModel: EmployeeOrderViewModel = koinViewModel(),
    onOrderClick: (Int) -> Unit = { orderId ->
        navController.navigate("${Strings.Routes.EMPLOYEE_ORDER_DETAIL}/$orderId")
    }
) {
    val orders by viewModel.orders.collectAsState(initial = emptyList())
    val currencyFormatter = remember { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(Strings.Labels.HOME) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = Strings.Labels.BACK
                        )
                    }
                },
                actions = {}
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(orders) { order: OrderEntity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clickable { onOrderClick(order.id.toInt()) }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(text = "Pedido #${order.id}")
                        Text(text = "Status: ${order.status.getDisplayLabel()}")
                        Text(text = "Total: ${currencyFormatter.format(order.total)}")
                    }
                }
            }
        }
    }
}
