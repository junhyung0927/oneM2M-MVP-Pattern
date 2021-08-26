package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.view.ae.INAEContract
import com.example.onem2m_inae_mvp.view.ae.INAEPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<INAEContract.Presenter> { (view: INAEContract.View) ->
        INAEPresenter(inAERepository = get(), view)
    }
}