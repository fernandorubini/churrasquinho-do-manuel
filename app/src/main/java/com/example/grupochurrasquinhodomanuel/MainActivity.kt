package com.example.grupochurrasquinhodomanuel

import com.example.grupochurrasquinhodomanuel.navigation.AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.grupochurrasquinhodomanuel.ui.theme.GrupoChurrasquinhoDoManuelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrupoChurrasquinhoDoManuelTheme {
                val navController = rememberNavController()
                AppNavigation(navController) // Passa o navController para a navegação
            }
        }
    }
}
