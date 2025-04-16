import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.grupochurrasquinhodomanuel.features.colaborador.ColaboradorHomeScreen
import com.example.grupochurrasquinhodomanuel.features.gestao.gestaoHomeScreenContent.GestaoHomeScreenContent
import com.example.grupochurrasquinhodomanuel.features.login.LoginScreen
import com.example.grupochurrasquinhodomanuel.features.telainicialcliente.ClienteHomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "register") {  // Alterado de "login" para "register"
        composable("register") {
            RegisterScreen(navController)  // Navega para a tela de registro
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("clienteHome") {
            ClienteHomeScreen()
        }
        composable("colaboradorHome") {
            ColaboradorHomeScreen()
        }
        composable("gestaoHome") {
            GestaoHomeScreenContent()
        }
    }
}
