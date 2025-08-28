package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    fun getAllEmployees(): Flow<List<Employee>>

    suspend fun getEmployeeById(id: Long): Employee?

    suspend fun insertEmployee(employee: Employee)

    suspend fun updateEmployee(employee: Employee)

    suspend fun updateEmployeeById(id: Long, employee: Employee)

    suspend fun toggleActive(id: Long)

    suspend fun deleteEmployee(employee: Employee)

    suspend fun deleteEmployeeById(id: Long)

    suspend fun clear()
}
