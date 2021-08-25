package com.example.onem2m_inae_mvp.service

import com.example.onem2m_in_ae.model.request.RequestAE
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface INAEDataService {
    @Headers(
        "Content-Type: application/json;ty=2",
    )
    @POST("/Mobius")
    suspend fun createAE(
        @Body param: RequestAE
    )
}