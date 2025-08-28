package com.example.grupochurrasquinhodomanuel.features.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.grupochurrasquinhodomanuel.features.register.ui.ClientRegisterScreen
import com.example.grupochurrasquinhodomanuel.core.constants.Strings

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(Strings.Routes.REGISTER) {
        ClientRegisterScreen(navController = navController)
    }
}
