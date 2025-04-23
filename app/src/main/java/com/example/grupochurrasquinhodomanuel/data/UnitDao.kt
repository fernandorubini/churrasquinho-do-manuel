package com.example.grupochurrasquinhodomanuel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UnitDao {

    @Insert
    suspend fun insert(unit: UnitEntity)

    @Query("SELECT * FROM units")
    suspend fun getAllUnits(): List<UnitEntity>
}
