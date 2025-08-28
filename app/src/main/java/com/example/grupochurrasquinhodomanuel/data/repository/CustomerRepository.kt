package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.data.dao.CustomerDao
import com.example.grupochurrasquinhodomanuel.model.Customer
import com.example.grupochurrasquinhodomanuel.data.mapper.toEntity
import com.example.grupochurrasquinhodomanuel.data.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CustomerRepository(private val customerDao: CustomerDao) {

    suspend fun insertCustomer(customer: Customer) {
        customerDao.insertCustomer(customer.toEntity())
    }

    suspend fun updateCustomer(customer: Customer) {
        customerDao.updateCustomer(customer.toEntity())
    }

    suspend fun deleteCustomer(customer: Customer) {
        customerDao.deleteCustomer(customer.toEntity())
    }

    fun getAllCustomers(): Flow<List<Customer>> {
        return customerDao.getAllCustomers()
            .map { list -> list.map { it.toModel() } }
    }

    fun getCustomerByEmail(email: String): Flow<Customer?> {
        return customerDao.getCustomerByEmail(email)
            .map { it?.toModel() }
    }

    suspend fun getCustomerById(id: Long): Customer? {
        return customerDao.getCustomerById(id)?.toModel()
    }
}
