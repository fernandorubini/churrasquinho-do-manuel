package com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.grupochurrasquinhodomanuel.features.order.presentation.OrderTrackingScreen

const val ORDER_TRACKING_ROUTE = "orderTracking"
private const val ARG_ORDER_ID = "orderId"
const val ORDER_TRACKING_PATH = "$ORDER_TRACKING_ROUTE/{$ARG_ORDER_ID}"


fun NavGraphBuilder.orderTrackingGraph(navController: NavController) {
    composable(
        route = ORDER_TRACKING_PATH,
        arguments = listOf(navArgument(ARG_ORDER_ID) { type = NavType.StringType })
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments?.getString(ARG_ORDER_ID)
            ?: return@composable
        OrderTrackingScreen(
            orderId = orderId,
            onBack = { navController.popBackStack() }
        )
    }
}

fun navigateToOrderTracking(navController: NavController, orderId: String) {
    navController.navigate("$ORDER_TRACKING_ROUTE/$orderId")
}
