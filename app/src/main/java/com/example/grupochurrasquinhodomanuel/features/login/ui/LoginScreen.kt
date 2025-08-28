package com.example.grupochurrasquinhodomanuel.features.login.ui

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.UserType
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.core.preferences.AuthPreferences
import com.example.grupochurrasquinhodomanuel.features.login.viewmodel.AuthState
import com.example.grupochurrasquinhodomanuel.features.login.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel(),
    authPreferences: AuthPreferences = koinInject()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val authState by viewModel.authState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                val userType = (authState as AuthState.Success).userType
                scope.launch {
                    authPreferences.saveUserEmail(email)
                    authPreferences.saveUserType(userType)
                }
                val route = when (userType) {
                    UserType.CUSTOMER -> Strings.Routes.CUSTOMER_HOME
                    UserType.EMPLOYEE -> Strings.Routes.EMPLOYEE_HOME
                    UserType.MANAGER -> Strings.Routes.MANAGEMENT_HOME
                }
                navController.navigate(route) { popUpTo(0) }
            }

            is AuthState.Error -> {
                errorMessage = (authState as AuthState.Error).message
                showDialog = true
            }

            else -> Unit
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(Strings.Labels.APP_NAME) }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = Strings.Labels.LOGIN,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(Strings.Labels.EMAIL) },
                singleLine = true,
                isError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                modifier = Modifier.fillMaxWidth()
            )

            if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Text(
                    text = Strings.Messages.INVALID_EMAIL,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(Strings.Labels.PASSWORD) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                        password.length >= 6
                    ) {
                        viewModel.login(email.trim(), password.trim())
                    } else {
                        errorMessage = Strings.Messages.FILL_ALL_FIELDS
                        showDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(Strings.Labels.LOGIN)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { navController.navigate(Strings.Routes.REGISTER) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(Strings.Labels.REGISTER)
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(Strings.Labels.LOGIN) },
                text = { Text(errorMessage) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) { Text("OK") }
                }
            )
        }
    }
}
