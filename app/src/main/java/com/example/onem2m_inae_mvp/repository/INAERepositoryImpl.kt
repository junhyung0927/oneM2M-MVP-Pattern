package com.example.onem2m_inae_mvp.repository

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestM2mAE
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.data.local.LocalDataSource
import com.example.onem2m_inae_mvp.data.remote.RemoteDataSource

class INAERepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
): INAERepository {
    companion object {
        val aeResourceName = "daewon_demo123"
    }

    override suspend fun createAE() {
        val requestAE = RequestAE(
            RequestM2mAE(
                aeResourceName,
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true
            )
        )
        return remoteDataSource.createAE(requestAE)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return remoteDataSource.getAEInfo()
    }
}