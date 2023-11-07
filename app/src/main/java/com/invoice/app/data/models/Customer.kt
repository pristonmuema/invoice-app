package com.invoice.app.data.models

import android.graphics.Bitmap
import com.google.firebase.firestore.IgnoreExtraProperties
import com.invoice.app.data.models.BaseModel

@IgnoreExtraProperties
data class Customer(
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    val img: String = ""
) : BaseModel() {

    fun getCompleteAddress(): String {
        return "$address\nphone: $phone\nemail: $email"
    }
}