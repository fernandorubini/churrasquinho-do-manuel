package com.example.grupochurrasquinhodomanuel.data

import com.example.grupochurrasquinhodomanuel.features.customer.model.Customer
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val customerDao: CustomerDao) {
    suspend fun insertCustomer(customer: Customer) = customerDao.insertCustomer(customer)
    suspend fun updateCustomer(customer: Customer) = customerDao.updateCustomer(customer)
    suspend fun deleteCustomer(customer: Customer) = customerDao.deleteCustomer(customer)
    fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAllCustomers()
    fun getCustomerByEmail(email: String): Flow<Customer?> = customerDao.getCustomerByEmail(email)
    suspend fun getCustomerById(id: Long): Customer? = customerDao.getCustomerById(id)
}