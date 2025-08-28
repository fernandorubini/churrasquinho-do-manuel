package com.example.grupochurrasquinhodomanuel.core.ui.brand

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.grupochurrasquinhodomanuel.core.model.Brand

@Composable
fun BrandLogo(
    brand: Brand,
    modifier: Modifier = Modifier,
    useSticker: Boolean = false,                 // false = logo de AppBar, true = sticker
    contentScale: ContentScale = ContentScale.Fit
) {
    val resId = if (useSticker) {
        BrandAssets.stickerRes(brand)
    } else {
        BrandAssets.logoResForAppBar(brand)      // <- nome correto
    }

    Image(
        painter = painterResource(resId),
        contentDescription = "Logomarca ${brand.name}",
        modifier = modifier,
        contentScale = contentScale
    )
}
