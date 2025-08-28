package com.example.grupochurrasquinhodomanuel.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Chave primária com auto incremento
    val brandId: Long,  // Relacionamento com a marca
    val name: String,  // Nome da unidade
    val location: String,  // Localização da unidade
    val brandName: String  // Nome da marca associada à unidade
)
