package com.example.grupochurrasquinhodomanuel.data.mapper

import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.data.entity.BrandEntity


fun Brand.toEntity(): BrandEntity =
    BrandEntity(
        id = 0L,
        name = this.displayName
    )

fun BrandEntity.toBrandOrNull(): Brand? =
    Brand.fromDisplayName(this.name)

fun BrandEntity.toBrandOrThrow(): Brand =
    Brand.fromDisplayName(this.name)
        ?: error("Brand desconhecida para displayName='${this.name}'")
