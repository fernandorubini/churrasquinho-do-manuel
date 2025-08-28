package com.example.grupochurrasquinhodomanuel.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidade Room para persistir marcas.
 *
 * Observações:
 * - A coluna [name] é única (índice com unique = true), pois usamos o displayName do enum [com.example.grupochurrasquinhodomanuel.core.model.Brand].
 * - [id] é autogerado pelo Room quando não for informado (0L).
 */
@Entity(
    tableName = "brands",
    indices = [Index(value = ["name"], unique = true)]
)
data class BrandEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String
)