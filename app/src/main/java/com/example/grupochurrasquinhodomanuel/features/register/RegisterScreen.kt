import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val auth = FirebaseAuth.getInstance()

    // Função para verificar se o e-mail já está registrado
    fun isEmailRegistered(email: String, onResult: (Boolean) -> Unit) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    onResult(result?.signInMethods?.isNotEmpty() == true)  // Se o e-mail já foi registrado
                } else {
                    onResult(false) // Caso ocorra algum erro
                }
            }
    }

    // Função para registrar o usuário
    fun registerUser() {
        if (password != confirmPassword) {
            errorMessage = "As senhas não coincidem!"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Por favor, insira um e-mail válido."
        } else {
            isEmailRegistered(email) { isRegistered ->
                if (isRegistered) {
                    errorMessage = "E-mail já está registrado."
                } else {
                    // Registrar novo usuário
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Se o cadastro for bem-sucedido, redireciona para a tela de login
                                navController.navigate("login")
                            } else {
                                errorMessage = task.exception?.message ?: "Erro desconhecido"
                            }
                        }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text("Cadastro do Cliente", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "E-mail") },
                    singleLine = true,
                    isError = email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Text(text = "E-mail inválido", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { registerUser() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cadastrar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Já sou Cliente! Faça o Login")
                }

                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    )
}
