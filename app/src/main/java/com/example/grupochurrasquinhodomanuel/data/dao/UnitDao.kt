package com.example.grupochurrasquinhodomanuel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.grupochurrasquinhodomanuel.core.model.UnitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UnitDao {

    @Insert
    suspend fun insert(unit: UnitEntity)

    @Query("SELECT * FROM units")
    fun getAllUnits(): Flow<List<UnitEntity>>

    @Query("SELECT * FROM units WHERE brandId = :brandId")
    fun getUnitsByBrand(brandId: Long): Flow<List<UnitEntity>>

    @Delete
    suspend fun deleteUnit(unit: UnitEntity)
}
