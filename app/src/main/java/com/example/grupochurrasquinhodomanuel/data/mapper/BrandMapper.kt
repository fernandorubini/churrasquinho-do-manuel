package com.example.grupochurrasquinhodomanuel.data.mapper

import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.data.entity.BrandEntity   // << aqui!

/** Domínio (enum Brand) -> Entidade Room. */
fun Brand.toEntity(): BrandEntity =
    BrandEntity(
        id = 0L,                    // autogerado
        name = this.displayName
    )

/** Entidade Room -> Domínio (enum) ou null. */
fun BrandEntity.toBrandOrNull(): Brand? =
    Brand.fromDisplayName(this.name)

/** Versão que lança se não encontrar a Brand. */
fun BrandEntity.toBrandOrThrow(): Brand =
    Brand.fromDisplayName(this.name)
        ?: error("Brand desconhecida para displayName='${this.name}'")
