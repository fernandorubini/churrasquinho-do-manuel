package com.example.grupochurrasquinhodomanuel.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val brandId: Long,
    val name: String,
    val location: String,
    val brandName: String
)
