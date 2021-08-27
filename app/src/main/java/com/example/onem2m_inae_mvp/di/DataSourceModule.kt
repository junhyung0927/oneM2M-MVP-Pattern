package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.data.local.INAELocalDataSource
import com.example.onem2m_inae_mvp.data.local.LocalDataSource
import com.example.onem2m_inae_mvp.data.remote.INAERemoteDataSource
import com.example.onem2m_inae_mvp.data.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        INAERemoteDataSource(inAEDataService = get())
    }

    single<LocalDataSource> {
        INAELocalDataSource(inAEDao = get())
    }
}