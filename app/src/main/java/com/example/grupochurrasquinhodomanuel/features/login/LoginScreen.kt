package com.example.grupochurrasquinhodomanuel.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.UserType
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }  // Usando null para inicializar a mensagem de erro
    var showDialog by remember { mutableStateOf(false) } // Controla a exibição do AlertDialog

    val loginState by viewModel.loginState.collectAsState()

    // Efeito para navegar quando o login for bem-sucedido
    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                val userType = (loginState as LoginState.Success).userType
                when (userType) {
                    UserType.CLIENTE -> navController.navigate("clienteHome")
                    UserType.COLABORADOR -> navController.navigate("colaboradorHome")
                    UserType.GESTAO -> navController.navigate("gestaoHome")
                }
            }

            is LoginState.Error -> {
                // Quando ocorrer um erro, mostra a mensagem de erro no AlertDialog
                errorMessage = (loginState as LoginState.Error).message
                showDialog = true // Exibe o dialog de erro
            }

            else -> Unit
        }
    }

    // Scaffold permite exibir Snackbar e organizar outros componentes na tela
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->  // Adicionando o parâmetro paddingValues aqui
            Column(
                modifier = Modifier
                    .padding(32.dp) // Padding adicional, se necessário
                    .fillMaxSize()
                    .padding(paddingValues) // Aplicando o paddingValues recebido
            ) {
                Text("Login", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))

                // E-mail
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    singleLine = true,
                    isError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty(), // Validação simples
                    modifier = Modifier.fillMaxWidth()
                )

                // Exibição de erro do campo de e-mail
                if (email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Text(text = "E-mail inválido", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Verifica se o e-mail é válido e a senha tem pelo menos 6 caracteres
                        if (email.isNotEmpty() && password.length >= 6) {
                            viewModel.login(email.trim(), password.trim())
                        } else {
                            errorMessage = "Por favor, insira um e-mail válido e uma senha com pelo menos 6 caracteres."
                            showDialog = true // Exibe o dialog de erro
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = loginState !is LoginState.Loading
                ) {
                    Text("Entrar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator()
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Adicionando o link para a tela de cadastro
                TextButton(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Não tem uma conta? Cadastre-se")
                }
            }

            // AlertDialog para exibir mensagens de erro
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Erro de Login") },
                    text = { Text(errorMessage ?: "Erro desconhecido") },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false // Fechar o dialog ao confirmar
                            }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    )
}
