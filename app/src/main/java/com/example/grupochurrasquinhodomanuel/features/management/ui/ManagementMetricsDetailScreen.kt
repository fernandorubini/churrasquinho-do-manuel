package com.example.grupochurrasquinhodomanuel.features.management.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.management.viewmodel.ManagementViewModel
import com.example.grupochurrasquinhodomanuel.ui.components.MetricCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ManagementMetricsDetailScreen(
    navController: NavController,
    viewModel: ManagementViewModel = koinViewModel()
) {
    val state by viewModel.dashboardState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhamento de Métricas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = Strings.Labels.BACK
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Resumo Detalhado",
                style = MaterialTheme.typography.headlineSmall
            )

            MetricCard(
                title = "Total de Pedidos em Aberto",
                value = state.activeOrders.toString()
            )

            MetricCard(
                title = "Tempo Médio de Entrega",
                value = state.averageDeliveryTime
            )

            MetricCard(
                title = "Satisfação Média dos Clientes",
                value = state.averageRating
            )

            MetricCard(
                title = "Total de Vendas Hoje",
                value = state.totalSalesToday
            )

            MetricCard(
                title = "Pedidos Concluídos",
                value = state.completedOrders.toString()
            )

            MetricCard(
                title = "Reclamações Recebidas",
                value = "1" // Você pode integrar com backend ou banco depois
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Esses dados são atualizados automaticamente com base no desempenho diário.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
