package com.example.grupochurrasquinhodomanuel.features.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.register.ui.EmployeeRegisterScreen

/** Rota da tela de cadastro de colaborador. */
fun NavGraphBuilder.employeeRegisterRoute(
    navController: NavHostController
) {
    composable(route = Strings.Routes.EMPLOYEE_REGISTER) {
        EmployeeRegisterScreen(navController = navController)
    }
}
