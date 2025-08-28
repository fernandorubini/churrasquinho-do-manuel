package com.example.grupochurrasquinhodomanuel.features.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.grupochurrasquinhodomanuel.features.register.ui.EmployeeTypeSelectionScreen
import com.example.grupochurrasquinhodomanuel.core.constants.Strings

fun NavGraphBuilder.employeeTypeSelectionRoute(navController: NavHostController) {
    composable(Strings.Routes.EMPLOYEE_TYPE_SELECTION) {
        EmployeeTypeSelectionScreen(navController = navController)
    }
}
