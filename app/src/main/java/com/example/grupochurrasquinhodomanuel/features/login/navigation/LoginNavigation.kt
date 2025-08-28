package com.example.grupochurrasquinhodomanuel.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.grupochurrasquinhodomanuel.features.login.ui.LoginScreen
import com.example.grupochurrasquinhodomanuel.core.constants.Strings

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(Strings.Routes.LOGIN) {
        LoginScreen(navController = navController)
    }
}
