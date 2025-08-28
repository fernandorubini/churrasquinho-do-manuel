package com.example.grupochurrasquinhodomanuel.features.customer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class CustomerBottomNavItem(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit
) {
    object Menu : CustomerBottomNavItem("menu", "Cardápio", { Icon(Icons.Default.Menu, contentDescription = "Cardápio") })
    object Cart : CustomerBottomNavItem("cart", "Carrinho", { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho") })
    object Favorites : CustomerBottomNavItem("favorites", "Favoritos", { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") })

    companion object {
        val items = listOf(Menu, Cart, Favorites)
    }
}

@Composable
fun CustomerBottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        CustomerBottomNavItem.items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = item.icon,
                label = { Text(item.label) }
            )
        }
    }
}
