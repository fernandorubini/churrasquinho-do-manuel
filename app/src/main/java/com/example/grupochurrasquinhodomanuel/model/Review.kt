package com.example.grupochurrasquinhodomanuel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacoes")
data class Review(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val produtoId: Long,  // Identifica o produto que está sendo avaliado
    val rating: Int,      // Usando Int ou Float para a nota
    val comentario: String? = null // Tornando o comentário opcional
)
