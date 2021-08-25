package com.example.onem2m_in_ae.model.response

import android.provider.CalendarContract

data class ResponseM2MCin(
    val parentId: String,
    val resourceId: String,
    val type: Int,
    val creationTime: String,
    val stateTag: Int,
    val resourceName: String,
    val lastModifiedTime: String,
    val expirationTime: String,
    val cs: Int,
    val cr: String,
    val contentInstances: String
)