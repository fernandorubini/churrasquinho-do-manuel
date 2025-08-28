package com.example.grupochurrasquinhodomanuel.core.ui.brand

import androidx.annotation.DrawableRes
import com.example.grupochurrasquinhodomanuel.R
import com.example.grupochurrasquinhodomanuel.core.model.Brand

object BrandAssets {

    @DrawableRes
    fun logoResForAppBar(brand: Brand): Int = when (brand) {
        Brand.CHURRASQUINHO -> R.drawable.churrasquinho_logo_appbar_transparent
        Brand.BUFFALOS_RED   -> R.drawable.buffalos_logo_appbar_transparent
        Brand.AI_OLI         -> R.drawable.ai_oli_logo_appbar_transparent
    }

    @DrawableRes
    fun stickerRes(brand: Brand): Int = when (brand) {
        Brand.CHURRASQUINHO -> R.drawable.churrasquinho_sticker_transparent
        Brand.BUFFALOS_RED   -> R.drawable.buffalos_sticker_transparent
        Brand.AI_OLI         -> R.drawable.ai_oli_sticker_transparent
    }
}
