package com.example.grupochurrasquinhodomanuel.features.customer.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.UUID

@Composable
fun OrderConfirmationScreen(navController: NavController) {
    val orderId = remember { UUID.randomUUID().toString().take(8).uppercase() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pedido Confirmado!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Número do pedido: $orderId", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.popBackStack("customerHome", inclusive = false) }) {
            Text("Voltar ao Início")
        }
    }
}
