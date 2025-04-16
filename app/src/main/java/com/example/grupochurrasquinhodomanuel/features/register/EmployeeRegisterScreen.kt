package com.example.grupochurrasquinhodomanuel.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun EmployeeRegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Função para verificar se o e-mail é corporativo e pertence a uma das lojas
    fun isCorporateEmail(email: String): Boolean {
        return email.endsWith("@churraquinhodomanuel.com.br") ||
                email.endsWith("@sushikai.com.br") ||
                email.endsWith("@aieoli.com.br") ||
                email.endsWith("@buffalosred.com.br")
    }

    // Função para registrar o novo funcionário no Firebase
    fun handleRegister() {
        if (!isCorporateEmail(email)) {
            errorMessage = "Por favor, use um e-mail corporativo."
        } else if (password == confirmPassword) {
            isLoading = true
            // Criando o usuário no Firebase Authentication
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Se o cadastro for bem-sucedido, navega para a tela de login
                        navController.navigate("login")
                    } else {
                        // Se houver erro, exibe a mensagem de erro
                        errorMessage = task.exception?.message ?: "Erro desconhecido"
                    }
                    isLoading = false
                }
        } else {
            errorMessage = "As senhas não coincidem!"
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .padding(paddingValues)
            ) {
                Text("Cadastro do Funcionário", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "E-mail Corporativo") },
                    singleLine = true,
                    isError = !isCorporateEmail(email), // Marcar campo de e-mail como erro se não for corporativo
                    modifier = Modifier.fillMaxWidth()
                )

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
                    onClick = { handleRegister() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading // Desabilitar o botão enquanto o cadastro está sendo processado
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Cadastrar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Adiciona a opção de login para quem já tem uma conta
                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Já sou Funcionário! Faça o Login")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage != null) {
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    )
}
