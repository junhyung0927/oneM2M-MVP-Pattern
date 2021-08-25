package com.example.onem2m_inae_mvp.di

import androidx.room.Room
import com.example.onem2m_inae_mvp.data.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().inAEDao()
    }
}