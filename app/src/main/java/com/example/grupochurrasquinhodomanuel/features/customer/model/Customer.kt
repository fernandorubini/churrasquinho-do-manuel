package com.example.grupochurrasquinhodomanuel.features.customer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class `Customer.kt`(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val email: String,
    val phone: String
)
