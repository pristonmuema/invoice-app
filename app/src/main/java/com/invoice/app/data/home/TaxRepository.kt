package com.invoice.app.data.home

import com.invoice.app.data.Resource
import com.invoice.app.data.models.Tax

interface TaxRepository {
    suspend fun getTaxes(): Resource<List<Tax>>
    suspend fun addTax(tax: Tax): Resource<Tax>
    suspend fun updateTax(tax: Tax): Resource<Tax>
    suspend fun deleteTax(id: String): Resource<Boolean>
}