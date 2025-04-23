package com.example.grupochurrasquinhodomanuel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grupochurrasquinhodomanuel.core.model.Brand

@Dao
interface BrandDao {

    @Insert
    suspend fun insert(brand: Brand)

    @Query("SELECT * FROM brands")
    suspend fun getAllBrands(): List<Brand>
}
