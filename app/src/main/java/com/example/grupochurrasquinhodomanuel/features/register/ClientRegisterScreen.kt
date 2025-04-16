package com.example.grupochurrasquinhodomanuel.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ClientRegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var triedToSubmit by remember { mutableStateOf(false) }

    @Composable
    fun getPasswordStrength(password: String): Triple<Float, String, Color> {
        return when {
            password.length >= 10 -> Triple(1.0f, "Senha Forte", Color(0xFF388E3C))
            password.length >= 6 -> Triple(0.6f, "Senha Média", Color(0xFFFBC02D))
            else -> Triple(0.3f, "Senha Fraca", MaterialTheme.colorScheme.error)
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun handleRegister() {
        when {
            !isEmailValid(email) -> {
                errorMessage = "Por favor, use um e-mail válido."
            }
            password.length < 6 -> {
                errorMessage = "A senha deve ter no mínimo 6 caracteres."
            }
            password != confirmPassword -> {
                errorMessage = "As senhas não coincidem!"
            }
            else -> {
                isLoading = true
                errorMessage = null
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("login")
                        } else {
                            errorMessage = task.exception?.message ?: "Erro desconhecido"
                        }
                        isLoading = false
                    }
            }
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
                Text("Cadastro do Cliente", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    singleLine = true,
                    isError = triedToSubmit && !isEmailValid(email),
                    trailingIcon = {
                        if (email.isNotBlank()) {
                            if (isEmailValid(email)) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF388E3C))
                            } else {
                                Icon(Icons.Default.Error, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (triedToSubmit && !isEmailValid(email)) {
                    Text(
                        text = "Por favor, insira um e-mail válido.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    trailingIcon = {
                        if (password.isNotBlank()) {
                            if (password.length >= 6) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF388E3C))
                            } else {
                                Icon(Icons.Default.Error, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (password.isNotBlank()) {
                    val (progress, label, color) = getPasswordStrength(password)
                    LinearProgressIndicator(
                        progress = progress,
                        color = color,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .padding(top = 4.dp)
                    )
                    Text(
                        text = label,
                        color = color,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    isError = triedToSubmit && password != confirmPassword,
                    trailingIcon = {
                        if (confirmPassword.isNotBlank()) {
                            if (password == confirmPassword) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF388E3C))
                            } else {
                                Icon(Icons.Default.Error, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (triedToSubmit && password != confirmPassword) {
                    Text(
                        text = "As senhas não coincidem.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        triedToSubmit = true
                        handleRegister()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text("Cadastrar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Já sou Cliente! Faça o Login")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage != null &&
                    isEmailValid(email) &&
                    password.length >= 6 &&
                    password == confirmPassword
                ) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    )
}
