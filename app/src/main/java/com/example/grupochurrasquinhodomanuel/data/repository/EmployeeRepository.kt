package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.features.employees.data.local.EmployeeDao
import com.example.grupochurrasquinhodomanuel.data.mapper.toEntity
import com.example.grupochurrasquinhodomanuel.data.mapper.toModel
import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    suspend fun insertEmployee(employee: Employee) {
        employeeDao.insertEmployee(employee.toEntity())
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee.toEntity())
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee.toEntity())
    }

    fun getAllEmployees(): Flow<List<Employee>> =
        employeeDao.getAllEmployees().map { list -> list.map { it.toModel() } }

    fun getEmployeeByEmail(email: String): Flow<Employee?> =
        employeeDao.getEmployeeByEmail(email).map { it?.toModel() }

    suspend fun getEmployeeById(id: Long): Employee? =
        employeeDao.getEmployeeById(id)?.toModel()
}
