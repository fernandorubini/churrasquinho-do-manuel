package com.example.grupochurrasquinhodomanuel.features.customer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.customer.ui.store.CustomerHomeScreen

fun NavGraphBuilder.addCustomerGraph(navController: NavHostController) {
    composable(Strings.Routes.CUSTOMER_HOME) {
        CustomerHomeScreen(navController)
    }
}
