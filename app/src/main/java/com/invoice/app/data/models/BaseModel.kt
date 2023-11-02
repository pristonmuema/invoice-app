package com.invoice.app.data.models

import com.invoice.app.data.utils.currentDateTime
import com.google.firebase.firestore.Exclude

abstract class BaseModel(
    @get:Exclude
    open var id: String = ""
) {
    var createdAt: Long = currentDateTime
    var updatedAt: Long = currentDateTime
}