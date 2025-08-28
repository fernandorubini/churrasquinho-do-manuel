package com.example.grupochurrasquinhodomanuel.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grupochurrasquinhodomanuel.core.UserType
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Routes
import com.example.grupochurrasquinhodomanuel.core.preferences.AuthPreferences
import com.example.grupochurrasquinhodomanuel.features.customer.ui.navigation.CustomerNavigationGraph
import com.example.grupochurrasquinhodomanuel.features.management.ui.EmployeeManagementScreen
import com.example.grupochurrasquinhodomanuel.features.login.ui.LoginScreen
import com.example.grupochurrasquinhodomanuel.features.management.ui.ManagementHomeScreen
import com.example.grupochurrasquinhodomanuel.features.register.ui.ClientRegisterScreen
import com.example.grupochurrasquinhodomanuel.features.register.ui.EmployeeRegisterScreen
import com.example.grupochurrasquinhodomanuel.features.register.ui.UserTypeSelectionScreen
import com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation.orderTrackingGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    authPreferences: AuthPreferences
) {
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val email = authPreferences.getUserEmail()
        val type = authPreferences.getUserType()
        startDestination = if (email != null && type != null) {
            when (type) {
                UserType.CUSTOMER -> Routes.CUSTOMER_HOME
                UserType.EMPLOYEE -> Routes.EMPLOYEE_HOME
                UserType.MANAGER  -> Routes.MANAGEMENT_HOME
            }
        } else {
            Routes.LOGIN
        }
    }

    startDestination?.let { start ->
        NavHost(
            navController = navController,
            startDestination = start
        ) {
            // Telas comuns
            composable(Routes.LOGIN) {
                LoginScreen(navController = navController)
            }
            composable(Routes.USER_TYPE_SELECTION) {
                UserTypeSelectionScreen(navController = navController)
            }
            composable(Routes.REGISTER) {
                ClientRegisterScreen(navController = navController)
            }

            // Cadastro de funcionário — **passa navController + viewModel**
            composable(Routes.EMPLOYEE_REGISTER) {
                EmployeeRegisterScreen(
                    navController = navController,
                    viewModel = koinViewModel()
                )
            }

            // Cliente (subgrafo)
            composable(Routes.CUSTOMER_HOME) {
                CustomerNavigationGraph(navController = navController)
            }

            // Funcionário
            composable(Routes.EMPLOYEE_HOME) {
                EmployeeManagementScreen(navController = navController)
            }

            // Gestão
            composable(Routes.MANAGEMENT_HOME) {
                ManagementHomeScreen(navController = navController)
            }

            // Rastreio de pedido
            orderTrackingGraph(navController)
        }
    }
}


@Preview(showBackground = true, name = "AppNavigation → Login")
@Composable
private fun Preview_AppNavigation_Login() {
    val nav = rememberNavController()
    val context = LocalContext.current
    val auth = remember { AuthPreferences(context) }
    AppNavigation(navController = nav, authPreferences = auth)
}
