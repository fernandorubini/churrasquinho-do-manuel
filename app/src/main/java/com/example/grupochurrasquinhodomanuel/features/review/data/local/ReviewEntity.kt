package com.example.grupochurrasquinhodomanuel.features.review.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: String,
    val customerName: String,
    val comment: String,
    val rating: Float
)
