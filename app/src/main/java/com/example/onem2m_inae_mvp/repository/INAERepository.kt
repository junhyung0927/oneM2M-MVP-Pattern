package com.example.onem2m_inae_mvp.repository

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.response.ResponseAE

interface INAERepository {
    //등록 및 생성
    suspend fun createAE()
    suspend fun registerContainerInstance(containerName: String, containerImage: Int, containerType: ContainerType)

    //조회
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContentInstanceDatabase() : List<ContainerInstance>
}