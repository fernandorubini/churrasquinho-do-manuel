package com.example.grupochurrasquinhodomanuel.features.customer.ui.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ClienteLojaScreen(navController: NavController) {
    val lojas = listOf("Loja Aeroporto", "Loja Venda Nova", "Loja Big Shopping")
    var selectedLoja by remember { mutableStateOf(lojas[0]) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
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

                // Lista de lojas com radio buttons
                Column(
                    modifier = Modifier.selectableGroup()
                ) {
                    lojas.forEach { loja ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = selectedLoja == loja,
                                onClick = { selectedLoja = loja }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = loja, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Redireciona para a tela do pedido com a loja escolhida
                        navController.navigate("pedido/${selectedLoja}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Escolher Loja")
                }
            }
        }
    )
}
