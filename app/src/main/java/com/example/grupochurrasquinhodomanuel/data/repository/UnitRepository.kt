package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.data.dao.UnitDao
import com.example.grupochurrasquinhodomanuel.core.model.UnitEntity
import kotlinx.coroutines.flow.Flow

class UnitRepository(private val unitDao: UnitDao) {

    suspend fun insert(unit: UnitEntity) {
        unitDao.insert(unit)
    }

    fun getAllUnits(): Flow<List<UnitEntity>> {
        return unitDao.getAllUnits()
    }
}
