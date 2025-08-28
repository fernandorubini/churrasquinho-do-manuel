package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.data.dao.BrandDao
import com.example.grupochurrasquinhodomanuel.data.mapper.toBrandOrNull
import com.example.grupochurrasquinhodomanuel.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BrandRepository(private val brandDao: BrandDao) {

    suspend fun insert(brand: Brand) {
        brandDao.insert(brand.toEntity())
    }

    fun getAllBrands(): Flow<List<Brand>> =
        brandDao.getAllBrands()
            .map { list -> list.mapNotNull { it.toBrandOrNull() } }

    suspend fun getBrandById(id: Long): Brand? =
        brandDao.getBrandById(id)?.toBrandOrNull()

    suspend fun deleteBrand(brand: Brand) {
        brandDao.deleteBrand(brand.toEntity())
    }
}
