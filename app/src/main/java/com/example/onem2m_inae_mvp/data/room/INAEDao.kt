package com.example.onem2m_inae_mvp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onem2m_in_ae.model.ContainerInstance

@Dao
interface INAEDao {
    @Query("SELECT * FROM container")
    fun getContainerInstanceInfoList(): List<ContainerInstance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createContainerInstance(containerInstance: List<ContainerInstance>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerContainerInstance(containerInstance: List<ContainerInstance>)

    @Query("DELETE FROM container")
    fun deleteAll()

    @Query("DELETE FROM container WHERE containerInstanceName = :resourceName ")
    fun deleteContainer(resourceName: String)
}