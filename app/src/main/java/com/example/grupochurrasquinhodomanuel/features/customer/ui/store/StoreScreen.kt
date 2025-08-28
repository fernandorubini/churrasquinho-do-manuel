package com.example.grupochurrasquinhodomanuel.features.customer.ui.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StoreScreen(navController: NavController) {
    val stores = listOf("Loja Aeroporto", "Loja Venda Nova", "Loja Big Shopping")
    var selectedStore by remember { mutableStateOf(stores[0]) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = "Escolha a loja",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.selectableGroup()) {
                stores.forEach { store ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = selectedStore == store,
                            onClick = { selectedStore = store }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = store, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("pedido/${selectedStore}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Escolher Loja")
            }
        }
    }
}
