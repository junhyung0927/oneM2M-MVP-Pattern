package com.example.onem2m_inae_mvp.data.local

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_inae_mvp.data.room.OneM2MDao

class OneM2MLocalDataSource(
    private val oneM2MDao: OneM2MDao
): LocalDataSource {
    override fun createContentInstance(containerInstance: List<ContainerInstance>) {
        oneM2MDao.createContainerInstance(containerInstance)
    }

    override suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>) {
        return oneM2MDao.registerContainerInstance(containerInstance)
    }

    override suspend fun getContainerInstanceDataBase(): List<ContainerInstance> {
        return oneM2MDao.getContainerInstanceInfoList()
    }

    override suspend fun deleteDatabaseContainer(resourceName: String) {
        return oneM2MDao.deleteContainer(resourceName)
    }
}