package com.example.grupochurrasquinhodomanuel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class Review(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val rating: Int,
    val comment: String? = null
)
