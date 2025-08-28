package com.example.grupochurrasquinhodomanuel.features.welcome.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Strings.Labels.APP_NAME,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                welcomeViewModel.disableFirstLaunch()
                navController.navigate(Strings.Routes.LOGIN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = Strings.Labels.LOGIN)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                welcomeViewModel.disableFirstLaunch()
                navController.navigate(Strings.Routes.REGISTER)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = Strings.Labels.REGISTER)
        }
    }
}
