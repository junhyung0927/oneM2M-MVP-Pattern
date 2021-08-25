package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestCnt(
    @SerializedName("m2m:cnt")
    val m2mcon: RequestM2MCnt
)