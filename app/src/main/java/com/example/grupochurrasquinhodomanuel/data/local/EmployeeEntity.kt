package com.example.grupochurrasquinhodomanuel.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val department: String,
    val isActive: Boolean = true
)
