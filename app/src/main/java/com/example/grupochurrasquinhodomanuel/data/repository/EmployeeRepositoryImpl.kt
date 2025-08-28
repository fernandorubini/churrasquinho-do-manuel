package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.data.dao.EmployeeDao
import com.example.grupochurrasquinhodomanuel.data.mapper.toEntity
import com.example.grupochurrasquinhodomanuel.data.mapper.toModel
import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmployeeRepositoryImpl(
    private val dao: EmployeeDao
) : EmployeeRepository {

    override fun getAllEmployees(): Flow<List<Employee>> =
        dao.observeAll().map { list -> list.map { it.toModel() } }

    override suspend fun getEmployeeById(id: Long): Employee? =
        dao.getById(id)?.toModel()

    override suspend fun insertEmployee(employee: Employee) {
        dao.upsert(employee.toEntity())
    }

    override suspend fun updateEmployee(employee: Employee) {
        updateEmployeeById(employee.id, employee)
    }

    override suspend fun updateEmployeeById(id: Long, employee: Employee) {
        val current = dao.getById(id)
        if (current == null) {
            dao.upsert(employee.toEntity())
        } else {
            val updated = current.copy(
                name = employee.name,
                email = employee.email,
                role = employee.role,
                department = employee.department
            )
            dao.upsert(updated)
        }
    }

    override suspend fun toggleActive(id: Long) {
        val current = dao.getById(id) ?: return
        dao.upsert(current.copy(isActive = !current.isActive))
    }

    override suspend fun deleteEmployee(employee: Employee) {
        deleteEmployeeById(employee.id)
    }

    override suspend fun deleteEmployeeById(id: Long) {
        dao.getById(id)?.let { dao.delete(it) }
    }

    override suspend fun clear() {
        dao.clear()
    }
}
