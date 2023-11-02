package com.invoice.app.data.home

import com.invoice.app.data.Resource
import com.invoice.app.data.models.Dashboard

interface DashboardRepository {
    suspend fun getDashboardInfo(): Resource<Dashboard>
}