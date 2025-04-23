package com.example.grupochurrasquinhodomanuel.data

import com.example.grupochurrasquinhodomanuel.core.model.Brand

class BrandRepository(private val brandDao: BrandDao) {

    suspend fun insert(brand: Brand) {
        brandDao.insert(brand)
    }

    suspend fun getAllBrands(): List<Brand> {
        return brandDao.getAllBrands()
    }
}
