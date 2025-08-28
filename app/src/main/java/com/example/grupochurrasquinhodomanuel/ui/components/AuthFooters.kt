package com.example.grupochurrasquinhodomanuel.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings

@Composable
fun AlreadyHaveAccountFooter(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = { navController.navigate(Strings.Routes.LOGIN) },
        modifier = modifier
    ) {
        Text(Strings.Labels.ALREADY_HAVE_ACCOUNT)
    }
}
