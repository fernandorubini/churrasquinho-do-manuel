package com.example.grupochurrasquinhodomanuel.features.review.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: String,        // Identificador do produto
    val customerName: String,     // Nome do cliente
    val comment: String,          // Comentário do cliente
    val rating: Float             // Avaliação do produto
)
