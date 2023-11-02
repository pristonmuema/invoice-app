package com.invoice.app.data.home

import com.invoice.app.data.Resource
import com.invoice.app.data.models.Business

interface MyBusinessRepository {
    suspend fun getMyBusinesses(): Resource<List<Business>>
    suspend fun canAddBusiness(): Boolean
    suspend fun addMyBusiness(business: Business): Resource<Business>
    suspend fun updateMyBusiness(business: Business): Resource<Business>
    suspend fun deleteMyBusiness(id: String): Resource<Boolean>
}