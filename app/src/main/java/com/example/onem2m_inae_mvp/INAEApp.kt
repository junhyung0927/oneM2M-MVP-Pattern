package com.example.onem2m_inae_mvp

import android.app.Application
import com.example.onem2m_in_ae.di.getNetworkModule
import com.example.onem2m_inae_mvp.di.dataSourceModule
import com.example.onem2m_inae_mvp.di.repositoryModule
import com.example.onem2m_inae_mvp.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class INAEApp: Application() {
    val MOBIUS_BASE_URL = "http://192.168.10.62:7579"

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@INAEApp)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(getNetworkModule(MOBIUS_BASE_URL))
            modules(roomModule)
        }
    }
}