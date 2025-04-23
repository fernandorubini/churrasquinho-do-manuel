package com.example.grupochurrasquinhodomanuel.features.management.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ManagementHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Painel da Gestão",
            style = MaterialTheme.typography.headlineMedium
        )

        // Exemplo de uso de métricas
        MetricCard(
            title = "Pedidos Ativos",
            value = "128"
        )

        MetricCard(
            title = "Tempo Médio de Entrega",
            value = "32 min"
        )

        MetricCard(
            title = "Satisfação dos Clientes",
            value = "92%"
        )
    }
}
