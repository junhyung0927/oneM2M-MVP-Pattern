package com.example.onem2m_inae_mvp.data.remote

import com.example.onem2m_in_ae.model.request.RequestAE

interface RemoteDataSource {
    suspend fun createAE(param: RequestAE)
}