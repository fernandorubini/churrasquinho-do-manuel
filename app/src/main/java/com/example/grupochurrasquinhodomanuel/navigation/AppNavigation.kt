package com.example.grupochurrasquinhodomanuel.navigation

import MenuScreen
import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.features.brand.presentation.BrandScreen
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.CustomerHomeScreen
import com.example.grupochurrasquinhodomanuel.features.login.ui.LoginScreen
import com.example.grupochurrasquinhodomanuel.features.employees.ui.home.EmployeeHomeScreen
import com.example.grupochurrasquinhodomanuel.features.management.ui.ManagementHomeScreen
import com.example.grupochurrasquinhodomanuel.features.unit.presentation.UnitScreen
import com.example.grupochurrasquinhodomanuel.features.customer.ui.cart.CartScreen
import com.example.grupochurrasquinhodomanuel.features.customer.ui.order.OrderConfirmationScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "register")
    {
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("customerHome") {
            CustomerHomeScreen(navController)
        }
        composable("employeeHome") {
            EmployeeHomeScreen()
        }
        composable("managementHome") {
            ManagementHomeScreen()
        }
        composable("brand") {
            BrandScreen()
        }
        composable("unit") {
            UnitScreen()
        }
        composable("cart") {
            CartScreen(navController)
        }
        composable("orderConfirmation") {
            OrderConfirmationScreen(navController)
        }
        composable("menu") {
            MenuScreen(navController)
        }
    }
}
