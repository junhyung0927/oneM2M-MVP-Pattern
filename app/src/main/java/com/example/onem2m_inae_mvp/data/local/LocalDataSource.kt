package com.example.onem2m_inae_mvp.data.local

import com.example.onem2m_in_ae.model.ContainerInstance

interface LocalDataSource {
    fun createContentInstance(containerInstance: List<ContainerInstance>)
    suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>)
    suspend fun getContainerInstanceDataBase(): List<ContainerInstance>
    suspend fun deleteDatabaseContainer(resourceName: String)
}