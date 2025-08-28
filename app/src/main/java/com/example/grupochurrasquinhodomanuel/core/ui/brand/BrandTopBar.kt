package com.example.grupochurrasquinhodomanuel.core.ui.brand

import androidx.compose.foundation.layout.height   // <- IMPORT IMPORTANTE
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.core.model.Brand

@Composable
fun BrandTopBar(
    brand: Brand,
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null,
    titleFallback: String? = null
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (showBack && onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = Strings.Labels.BACK
                    )
                }
            }
        },
        title = {
            if (titleFallback == null) {
                BrandLogo(
                    brand = brand,
                    modifier = Modifier.height(24.dp)
                )
            } else {
                Text(text = titleFallback)
            }
        }
    )
}
