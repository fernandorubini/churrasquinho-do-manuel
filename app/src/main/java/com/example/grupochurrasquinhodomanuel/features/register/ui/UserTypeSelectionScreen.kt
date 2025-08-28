package com.example.grupochurrasquinhodomanuel.features.register.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings

@Composable
fun UserTypeSelectionScreen(navController: NavController) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quem é você?",
                    style = MaterialTheme.typography.headlineMedium
                )

                Button(
                    onClick = { navController.navigate("clientRegister") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sou Cliente")
                }

                TextButton(
                    onClick = {
                        navController.navigate(Strings.Routes.EMPLOYEE_TYPE_SELECTION)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sou Funcionário")
                }


                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Já tenho conta. Fazer Login")
                }
            }
        }
    }
}
