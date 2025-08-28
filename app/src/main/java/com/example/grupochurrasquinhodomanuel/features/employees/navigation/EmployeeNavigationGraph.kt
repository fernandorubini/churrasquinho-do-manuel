package com.example.grupochurrasquinhodomanuel.features.employees.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.grupochurrasquinhodomanuel.features.employees.ui.home.EmployeeHomeScreen
import com.example.grupochurrasquinhodomanuel.features.employees.ui.EmployeeOrderDetailScreen

fun NavGraphBuilder.addEmployeeGraph(navController: NavHostController) {
    navigation(
        route = "employeeGraph",
        startDestination = "employeeHome"
    ) {
        composable("employeeHome") {
            EmployeeHomeScreen(navController)
        }
        composable("employeeOrderDetail/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")?.toInt() ?: 0
            EmployeeOrderDetailScreen(orderId = orderId, navController = navController)
        }
    }
}
