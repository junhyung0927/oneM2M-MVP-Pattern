package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.data.local.OneM2MLocalDataSource
import com.example.onem2m_inae_mvp.data.local.LocalDataSource
import com.example.onem2m_inae_mvp.data.remote.OneM2MRemoteDataSource
import com.example.onem2m_inae_mvp.data.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        OneM2MRemoteDataSource(oneM2MService = get())
    }

    single<LocalDataSource> {
        OneM2MLocalDataSource(oneM2MDao = get())
    }
}