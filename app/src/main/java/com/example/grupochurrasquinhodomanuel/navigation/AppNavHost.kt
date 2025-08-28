package com.example.grupochurrasquinhodomanuel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.login.ui.LoginScreen
import com.example.grupochurrasquinhodomanuel.features.management.ui.ManagementHomeScreen
import com.example.grupochurrasquinhodomanuel.features.register.navigation.employeeRegisterRoute
import com.example.grupochurrasquinhodomanuel.features.register.ui.ClientRegisterScreen
import com.example.grupochurrasquinhodomanuel.features.register.ui.UserTypeSelectionScreen
import com.example.grupochurrasquinhodomanuel.features.splash.presentation.SplashScreen
import com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation.orderTrackingGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Strings.Routes.SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash / boas-vindas
        composable(Strings.Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        // Seleção de tipo de usuário
        composable(Strings.Routes.USER_TYPE_SELECTION) {
            UserTypeSelectionScreen(navController = navController)
        }

        // Autenticação / cadastro
        composable(Strings.Routes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(Strings.Routes.REGISTER) {
            ClientRegisterScreen(navController = navController)
        }

        // Cadastro de funcionário (usa navController internamente)
        employeeRegisterRoute(navController)

        // Homes
        composable(Strings.Routes.MANAGEMENT_HOME) {
            ManagementHomeScreen(navController = navController)
        }
        // composable(Strings.Routes.EMPLOYEE_HOME) { EmployeeHomeScreen(navController) }
        // composable(Strings.Routes.CUSTOMER_HOME) { CustomerHomeScreen(navController) }

        // Rota de rastreamento (fonte única da verdade)
        orderTrackingGraph(navController)
    }
}

/* ------------------------------ Preview ------------------------------ */

@Preview(showBackground = true, name = "AppNavHost → Login")
@Composable
private fun Preview_AppNavHost_Login() {
    val nav = rememberNavController()
    AppNavHost(
        navController = nav,
        startDestination = Strings.Routes.LOGIN
    )
}
