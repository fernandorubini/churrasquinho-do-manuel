package com.example.grupochurrasquinhodomanuel.features.customer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Routes
import com.example.grupochurrasquinhodomanuel.features.customer.ui.favorite.FavoriteScreen
import com.example.grupochurrasquinhodomanuel.features.customer.ui.store.CustomerHomeScreen
import com.example.grupochurrasquinhodomanuel.features.order.presentation.OrderTrackingScreen

@Composable
fun CustomerNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.CUSTOMER_HOME,
        modifier = modifier
    ) {
        composable(Routes.CUSTOMER_HOME) {
            CustomerHomeScreen(navController = navController)
        }

        composable(Routes.FAVORITES_ROUTE) {
            FavoriteScreen()
        }

        composable(
            route = Routes.ORDER_TRACKING_PATH,
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId").orEmpty()
            OrderTrackingScreen(
                orderId = orderId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/* ------------------------------ Previews ------------------------------ */

@Preview(showBackground = true, name = "Customer Graph – Home")
@Composable
private fun Preview_CustomerGraph_Home() {
    val nav = rememberNavController()
    CustomerNavigationGraph(navController = nav)
}

@Preview(showBackground = true, name = "Customer Graph – Favoritos only")
@Composable
private fun Preview_CustomerGraph_FavoritesOnly() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.FAVORITES_ROUTE) {
        composable(Routes.FAVORITES_ROUTE) { FavoriteScreen() }
    }
}
