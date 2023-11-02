
package com.invoice.app.data.home

import com.invoice.app.data.Resource
import com.invoice.app.data.models.Invoice

interface InvoiceRepository {
    suspend fun getInvoices(): Resource<List<Invoice>>
    suspend fun addInvoice(invoice: Invoice): Resource<Invoice>
    suspend fun updateInvoice(invoice: Invoice): Resource<Invoice>
    suspend fun deleteInvoice(id: String): Resource<Boolean>
    suspend fun updatePaidState(id: String, isPaid: Boolean): Resource<Boolean>
}