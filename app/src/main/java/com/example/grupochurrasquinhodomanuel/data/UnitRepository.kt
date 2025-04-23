package com.example.grupochurrasquinhodomanuel.data

class UnitRepository(private val unitDao: UnitDao) {

    suspend fun insert(unit: UnitEntity) {
        unitDao.insert(unit)
    }

    suspend fun getAllUnits(): List<UnitEntity> {
        return unitDao.getAllUnits()
    }
}
