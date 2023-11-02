package com.invoice.app.data.models

import com.google.firebase.firestore.IgnoreExtraProperties
import com.invoice.app.data.models.BaseModel

@IgnoreExtraProperties
data class Business(
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = ""
) : BaseModel() {

    fun getCompleteAddress(): String {
        return "$address\nphone: $phone\nemail: $email"
    }
}