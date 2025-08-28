package com.example.grupochurrasquinhodomanuel.features.management.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.ui.components.MetricCard

@Composable
fun MetricsSection(
    totalSalesToday: String,
    completedOrders: Int,
    complaints: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        MetricCard(
            title = Strings.Labels.TOTAL_DAILY_SALES,
            value = totalSalesToday
        )
        MetricCard(
            title = Strings.Labels.COMPLETED_ORDERS,
            value = completedOrders.toString()
        )
        MetricCard(
            title = Strings.Labels.COMPLAINTS_RECEIVED,
            value = complaints
        )
    }
}
