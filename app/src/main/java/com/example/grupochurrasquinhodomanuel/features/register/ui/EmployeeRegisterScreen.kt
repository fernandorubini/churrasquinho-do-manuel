package com.example.grupochurrasquinhodomanuel.features.register.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.core.util.getPasswordStrength
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeRegisterScreen(
    navController: NavController,
    viewModel: EmployeeRegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var tried by remember { mutableStateOf(false) }

    fun isEmailValid(e: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches()

    val minName = 3
    val minRole = 3

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Strings.Labels.REGISTER) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(Strings.Labels.NAME) },
                singleLine = true,
                isError = tried && name.trim().length < minName,
                modifier = Modifier.fillMaxWidth()
            )
            if (tried && name.trim().length < minName) {
                Text(
                    text = Strings.Messages.NAME_TOO_SHORT.format(minName),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(Strings.Labels.EMAIL) },
                singleLine = true,
                isError = tried && !isEmailValid(email),
                modifier = Modifier.fillMaxWidth()
            )
            if (tried && !isEmailValid(email)) {
                Text(
                    text = Strings.Messages.INVALID_EMAIL,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = role,
                onValueChange = { role = it },
                label = { Text("Função") },
                singleLine = true,
                isError = tried && role.trim().length < minRole,
                modifier = Modifier.fillMaxWidth()
            )
            if (tried && role.trim().length < minRole) {
                Text(
                    text = Strings.Messages.ROLE_TOO_SHORT.format(minRole),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Departamento") },
                singleLine = true,
                isError = tried && department.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )
            if (tried && department.isBlank()) {
                Text(
                    text = Strings.Messages.DEPARTMENT_REQUIRED,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(Strings.Labels.PASSWORD) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
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
                )
                Text(text = label, color = color, style = MaterialTheme.typography.bodySmall)
            }

            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it },
                label = { Text(Strings.Labels.CONFIRM_PASSWORD) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = tried && password != confirm,
                modifier = Modifier.fillMaxWidth()
            )
            if (tried && password != confirm) {
                Text(
                    text = Strings.Messages.PASSWORD_MISMATCH,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    tried = true
                    val ok =
                        name.trim().length >= minName &&
                                isEmailValid(email) &&
                                role.trim().length >= minRole &&
                                department.isNotBlank() &&
                                password.length >= 6 &&
                                password == confirm

                    if (!ok) return@Button


                    viewModel.register(
                        name = name.trim(),
                        email = email.trim(),
                        password = password,
                        confirmPassword = confirm
                    ) {
                        Toast.makeText(
                            context,
                            Strings.Labels.SUCCESS_REGISTER,
                            Toast.LENGTH_LONG
                        ).show()


                        navController.navigate(Strings.Routes.LOGIN) { popUpTo(0) }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(Strings.Labels.REGISTER)
            }

            Spacer(Modifier.height(8.dp))

            AlreadyHaveAccountFooter(
                onClick = { navController.navigate(Strings.Routes.LOGIN) }
            )
        }
    }
}

@Composable
private fun AlreadyHaveAccountFooter(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        TextButton(onClick = onClick) {
            Text(Strings.Labels.ALREADY_HAVE_ACCOUNT)
        }
    }
}
