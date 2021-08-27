package com.example.onem2m_inae_mvp.data.remote

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.service.INAEDataService

class INAERemoteDataSource(
    val inAEDataService: INAEDataService
): RemoteDataSource {
    override suspend fun createAE(param: RequestAE) {
        return inAEDataService.createAE(param)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return inAEDataService.getAEInfo()
    }
}