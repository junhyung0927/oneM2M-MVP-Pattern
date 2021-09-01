package com.example.onem2m_inae_mvp.repository

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.request.*
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.data.local.LocalDataSource
import com.example.onem2m_inae_mvp.data.remote.RemoteDataSource
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID

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

    override suspend fun registerContainerInstance(
        containerName: String,
        containerImage: Int,
        containerType: ContainerType
    ) {
        val containerInstance = listOf(
            ContainerInstance(
                containerInstanceName = containerName,
                containerImage = containerImage,
                type = containerType
            )
        )

        return localDataSource.registerContainerInstance(containerInstance)
    }

    override suspend fun createSubscription(resourceName: String) {
        //cr -> x-m2m-origin 헤더값
        val subName = APP_ID
        val requestSub = RequestSub(
            RequestM2MSub(
                "sub",
                RequestEncNet(
                    arrayListOf(3, 4)
                ),
                arrayListOf("mqtt://192.168.10.62/${subName}_${resourceName}"),
                1,
                2,
                subName,
                100
            )
        )
        return remoteDataSource.createSubscription(requestSub, resourceName)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return remoteDataSource.getAEInfo()
    }

    override suspend fun getContainerInfo(): ResponseCnt {
        return remoteDataSource.getContainerInfo()
    }

    override suspend fun getContentInstanceInfo(resourceName: String): ResponseCin {
        return remoteDataSource.getContentInstanceInfo(resourceName)
    }

    override suspend fun getContentInstanceDatabase(): List<ContainerInstance> {
        return localDataSource.getContainerInstanceDataBase()
    }

    override suspend fun getChildResourceInfo(): ResponseCntUril {
        return remoteDataSource.getChildResourceInfo()
    }

    override suspend fun deviceControl(content: String, resourceName: String) {
        val contentInstance = RequestCin(
            RequestM2MCin(
                content
            )
        )
        return remoteDataSource.deviceControl(contentInstance, resourceName)
    }

    override suspend fun deleteDatabaseContainer(resourceName: String) {
        return localDataSource.deleteDatabaseContainer(resourceName)
    }
}