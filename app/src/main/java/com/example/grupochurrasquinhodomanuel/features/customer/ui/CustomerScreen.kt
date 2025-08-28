package com.example.grupochurrasquinhodomanuel.features.customer.ui.store

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.grupochurrasquinhodomanuel.features.customer.ui.navigation.CustomerNavigationGraph

// Definição dos destinos da bottom bar para o cliente
sealed class CustomerBottomBarScreen(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : CustomerBottomBarScreen("customerHome", "Início", Icons.Default.Home)
    object Favorites : CustomerBottomBarScreen("favorites", "Favoritos", Icons.Default.Favorite)
    object OrderTracking : CustomerBottomBarScreen("orderTracking", "Pedidos", Icons.Default.LocalShipping)
}

@Composable
fun CustomerScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        CustomerBottomBarScreen.Home,
        CustomerBottomBarScreen.Favorites,
        CustomerBottomBarScreen.OrderTracking
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        CustomerNavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
