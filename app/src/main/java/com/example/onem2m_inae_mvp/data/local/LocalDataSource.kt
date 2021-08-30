package com.example.onem2m_inae_mvp.data.local

import com.example.onem2m_in_ae.model.ContainerInstance

interface LocalDataSource {
    suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>)
    suspend fun getContainerInstanceDataBase(): List<ContainerInstance>
}