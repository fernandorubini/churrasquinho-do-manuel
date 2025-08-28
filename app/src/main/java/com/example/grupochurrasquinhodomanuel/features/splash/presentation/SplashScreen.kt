package com.example.grupochurrasquinhodomanuel.features.splash.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.features.welcome.WelcomePreferences
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    navController: NavController,
    welcomePreferences: WelcomePreferences
) {
    LaunchedEffect(Unit) {
        // Simula tempo de splash
        delay(2000)

        // Captura o valor apenas uma vez
        val isFirstLaunch = welcomePreferences.isFirstLaunch.first()

        val user = FirebaseAuth.getInstance().currentUser

        when {
            isFirstLaunch -> {
                navController.navigate("welcome") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            user != null -> {
                navController.navigate("storeHome") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            else -> {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Churrasquinho do Manuel",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
