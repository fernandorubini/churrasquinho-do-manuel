package com.example.grupochurrasquinhodomanuel.features.splash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(1200)
        navController.navigate(Strings.Routes.USER_TYPE_SELECTION) {
            popUpTo(Strings.Routes.SPLASH) { inclusive = true }
        }
    }
    SplashContent()
}

@Composable
private fun SplashContent() {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Strings.Labels.APP_NAME,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = Strings.Labels.WELCOME,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Preview(showBackground = true, name = "Splash – conteúdo")
@Composable
private fun Preview_SplashContent() {
    SplashContent()
}

@Preview(showBackground = true, name = "Splash – com Nav (estático)")
@Composable
private fun Preview_Splash_WithNav() {
    val nav = rememberNavController()
    SplashContent()
}
