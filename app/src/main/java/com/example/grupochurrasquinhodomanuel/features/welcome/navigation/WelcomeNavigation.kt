package com.example.grupochurrasquinhodomanuel.features.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.welcome.presentation.WelcomeScreen

fun NavGraphBuilder.welcomeRoute(navController: NavHostController) {
    composable(route = Strings.Routes.WELCOME) {
        // Se WelcomeScreen aceita navController:
        WelcomeScreen(navController = navController)
    }
}
