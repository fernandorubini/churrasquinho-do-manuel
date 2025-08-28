package com.example.grupochurrasquinhodomanuel.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grupochurrasquinhodomanuel.data.entity.BrandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BrandEntity)

    // use a coluna 'name' que existe no BrandEntity
    @Query("SELECT * FROM brands ORDER BY name ASC")
    fun getAllBrands(): Flow<List<BrandEntity>>

    @Query("SELECT * FROM brands WHERE id = :id LIMIT 1")
    suspend fun getBrandById(id: Long): BrandEntity?

    @Delete
    suspend fun deleteBrand(entity: BrandEntity)

    // (Opcional) apagar por id:
    // @Query("DELETE FROM brands WHERE id = :id")
    // suspend fun deleteById(id: Long)
}
