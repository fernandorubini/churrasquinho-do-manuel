package com.example.grupochurrasquinhodomanuel.features.customer.ui.store

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.CustomerViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CustomerHomeScreen(
    navController: NavHostController,
    viewModel: CustomerViewModel = koinViewModel()
) {
    val customer by viewModel.customer.collectAsState()
    val customerName = customer?.name ?: "Bem-vindo!"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = customerName,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("favorites") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Favoritos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("orderTracking") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Rastrear Pedido")
        }
    }
}
