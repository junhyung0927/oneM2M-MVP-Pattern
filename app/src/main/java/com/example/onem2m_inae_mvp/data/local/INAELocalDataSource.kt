package com.example.onem2m_inae_mvp.data.local

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_inae_mvp.data.room.INAEDao

class INAELocalDataSource(
    private val inAEDao: INAEDao
): LocalDataSource {
    override fun createContentInstance(containerInstance: List<ContainerInstance>) {
        inAEDao.createContainerInstance(containerInstance)
    }

    override suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>) {
        return inAEDao.registerContainerInstance(containerInstance)
    }

    override suspend fun getContainerInstanceDataBase(): List<ContainerInstance> {
        return inAEDao.getContainerInstanceInfoList()
    }

    override suspend fun deleteDatabaseContainer(resourceName: String) {
        return inAEDao.deleteContainer(resourceName)
    }
}