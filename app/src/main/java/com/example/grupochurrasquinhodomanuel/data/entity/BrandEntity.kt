package com.example.grupochurrasquinhodomanuel.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "brands",
    indices = [Index(value = ["name"], unique = true)]
)
data class BrandEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String
)