package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.repository.INAERepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<INAERepository> {
        INAERepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}