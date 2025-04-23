package com.example.grupochurrasquinhodomanuel.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands")
data class Brand(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)
