package com.example.grupochurrasquinhodomanuel.core.util

import androidx.compose.ui.graphics.Color

fun getPasswordStrength(password: String): Triple<Float, String, Color> {
    return when {
        password.length >= 10 -> Triple(1.0f, "Senha Forte", Color(0xFF388E3C))
        password.length >= 6 -> Triple(0.6f, "Senha Média", Color(0xFFFBC02D))
        else -> Triple(0.3f, "Senha Fraca", Color.Red)
    }
}
