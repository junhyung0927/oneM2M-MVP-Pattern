package com.example.onem2m_inae_mvp.data.remote

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.response.ResponseAE

interface RemoteDataSource {
    suspend fun createAE(param: RequestAE)

    suspend fun getAEInfo(): ResponseAE
}