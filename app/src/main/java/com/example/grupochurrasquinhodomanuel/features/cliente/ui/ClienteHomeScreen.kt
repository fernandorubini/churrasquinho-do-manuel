package com.example.grupochurrasquinhodomanuel.features.cliente.ui.clientHome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ClienteHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tela Inicial do Cliente", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Aqui você pode navegar para outra tela, como "detalhesProduto"
                navController.navigate("detalhesProduto")
            }
        ) {
            Text("Ir para Detalhes do Produto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Adicione navegação para outras telas, se necessário
                navController.navigate("historicoPedidos")
            }
        ) {
            Text("Ver Histórico de Pedidos")
        }
    }
}
