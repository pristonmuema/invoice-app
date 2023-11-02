
package com.invoice.app.data.home

import com.invoice.app.data.Resource
import com.invoice.app.data.models.Customer

interface CustomersRepository {
    suspend fun getCustomers(): Resource<List<Customer>>
    suspend fun addCustomer(customer: Customer): Resource<Customer>
    suspend fun updateCustomer(customer: Customer): Resource<Customer>
    suspend fun deleteCustomer(id: String): Resource<Boolean>
}