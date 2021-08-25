package com.example.onem2m_inae_mvp.data.remote

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_inae_mvp.service.INAEDataService

class INAERemoteDataSource(
    val inaeDataService: INAEDataService
): RemoteDataSource {
    override suspend fun createAE(param: RequestAE) {
        return inaeDataService.createAE(param)
    }
}