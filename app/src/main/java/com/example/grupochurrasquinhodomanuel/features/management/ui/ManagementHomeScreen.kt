package com.example.grupochurrasquinhodomanuel.features.management.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.management.viewmodel.ManagementViewModel
import com.example.grupochurrasquinhodomanuel.ui.components.MetricCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ManagementHomeScreen(
    navController: NavController,
    viewModel: ManagementViewModel = koinViewModel()
) {
    // ✅ use collectAsStateWithLifecycle e garanta o import de getValue
    val state by viewModel.dashboardState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Usei um título existente para evitar Strings.Titles.* inexistente
        Text(
            text = Strings.Labels.EMPLOYEE_MANAGEMENT_TITLE, // "Gestão de Colaboradores"
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Métricas principais (rótulos textuais diretos por enquanto)
        MetricCard(
            title = "Pedidos ativos",
            value = state.activeOrders.toString()
        )

        MetricCard(
            title = "Tempo médio de entrega",
            value = state.averageDeliveryTime
        )

        MetricCard(
            title = "Satisfação do cliente",
            value = state.averageRating
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Resumo adicional
        MetricCard(
            title = "Vendas (hoje)",
            value = state.totalSalesToday
        )

        MetricCard(
            title = "Pedidos concluídos",
            value = state.completedOrders.toString()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão de navegação (usei uma rota existente para compilar)
        Button(onClick = { navController.navigate(Strings.Routes.MANAGEMENT_HOME) }) {
            Text("Ver Detalhes")
        }
    }
}
