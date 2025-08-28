package com.example.grupochurrasquinhodomanuel.features.register.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings as S
import com.example.grupochurrasquinhodomanuel.core.util.getPasswordStrength
import com.example.grupochurrasquinhodomanuel.features.register.presentation.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ClientRegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var triedToSubmit by remember { mutableStateOf(false) }

    fun isEmailValid(e: String) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()

    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(
                // Usamos um label existente
                title = { Text(S.Labels.REGISTER) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            // E-mail
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(S.Labels.EMAIL) },
                singleLine = true,
                isError = triedToSubmit && !isEmailValid(email),
                trailingIcon = {
                    if (email.isNotBlank()) {
                        androidx.compose.material3.Icon(
                            imageVector = if (isEmailValid(email)) Icons.Filled.CheckCircle else Icons.Filled.Error,
                            contentDescription = null,
                            tint = if (isEmailValid(email)) Color(0xFF388E3C) else MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (triedToSubmit && !isEmailValid(email)) {
                Text(
                    text = S.Messages.INVALID_EMAIL, // ✅ já existia no projeto
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Senha
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(S.Labels.PASSWORD) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                trailingIcon = {
                    if (password.isNotBlank()) {
                        androidx.compose.material3.Icon(
                            imageVector = if (password.length >= 6) Icons.Filled.CheckCircle else Icons.Filled.Error,
                            contentDescription = null,
                            tint = if (password.length >= 6) Color(0xFF388E3C) else MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (password.isNotBlank()) {
                val (progress, label, color) = getPasswordStrength(password)
                LinearProgressIndicator(
                    progress = { progress },
                    color = color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    text = label,
                    color = color,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Confirmar senha
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(S.Labels.CONFIRM_PASSWORD) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = triedToSubmit && password != confirmPassword,
                trailingIcon = {
                    if (confirmPassword.isNotBlank()) {
                        androidx.compose.material3.Icon(
                            imageVector = if (password == confirmPassword) Icons.Filled.CheckCircle else Icons.Filled.Error,
                            contentDescription = null,
                            tint = if (password == confirmPassword) Color(0xFF388E3C) else MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (triedToSubmit && password != confirmPassword) {
                Text(
                    text = "As senhas não conferem.", // TODO: mover para Strings.Messages.PASSWORD_MISMATCH
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão registrar
            Button(
                onClick = {
                    triedToSubmit = true
                    if (isEmailValid(email) && password.length >= 6 && password == confirmPassword) {
                        viewModel.register(email, password, confirmPassword) {
                            Toast.makeText(
                                context,
                                S.Labels.SUCCESS_REGISTER, // ✅ existe no seu Strings
                                Toast.LENGTH_LONG
                            ).show()
                            navController.navigate(S.Routes.LOGIN) { popUpTo(0) }
                        }
                    }
                },
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(S.Labels.REGISTER) // ✅ texto, não rota
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link para login
            TextButton(
                onClick = { navController.navigate(S.Routes.LOGIN) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Já tem uma conta? Entrar") // TODO: mover para Strings.Labels.ALREADY_HAVE_ACCOUNT
            }
        }
    }
}
