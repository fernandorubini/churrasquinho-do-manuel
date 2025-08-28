package com.example.grupochurrasquinhodomanuel.features.management.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.management.ui.ManagementHomeScreen
import com.example.grupochurrasquinhodomanuel.features.management.ui.ManagementMetricsDetailScreen

fun NavGraphBuilder.addManagementGraph(navController: NavHostController) {
    composable(Strings.Routes.MANAGEMENT_HOME) {
        ManagementHomeScreen(navController = navController)
    }
    composable(Strings.Routes.MANAGEMENT_METRICS_DETAIL) {
        ManagementMetricsDetailScreen(navController = navController)
    }
}
