package com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.grupochurrasquinhodomanuel.features.order.presentation.OrderTrackingScreen

// Rota pública para usar na navegação
const val ORDER_TRACKING_ROUTE = "orderTracking"
private const val ARG_ORDER_ID = "orderId"
const val ORDER_TRACKING_PATH = "$ORDER_TRACKING_ROUTE/{$ARG_ORDER_ID}"

/**
 * Adiciona a rota de rastreamento de pedido ao seu NavHost.
 * Chame dentro do AppNavHost.
 */
fun NavGraphBuilder.orderTrackingGraph(navController: NavController) {
    composable(
        route = ORDER_TRACKING_PATH,
        arguments = listOf(navArgument(ARG_ORDER_ID) { type = NavType.StringType })
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getString(ARG_ORDER_ID)
            ?: return@composable
        OrderTrackingScreen(
            orderId = orderId,
            onBack = { navController.popBackStack() } // ✅ agora passamos o callback
        )
    }
}

/** Helper para navegar para a tela a partir de qualquer ponto. */
fun navigateToOrderTracking(navController: NavController, orderId: String) {
    navController.navigate("$ORDER_TRACKING_ROUTE/$orderId")
}
