package com.example.onem2m_inae_mvp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onem2m_in_ae.model.ContainerInstance

@Database(entities = [ContainerInstance::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "AE-DATABASE"
    }

    abstract fun inAEDao(): OneM2MDao
}