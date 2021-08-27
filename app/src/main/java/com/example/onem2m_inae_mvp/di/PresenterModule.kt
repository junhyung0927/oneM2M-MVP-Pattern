package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.view.main.INAEContract
import com.example.onem2m_inae_mvp.view.main.INAEPresenter
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterContract
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<INAEContract.Presenter> { (view: INAEContract.View) ->
        INAEPresenter(inAERepository = get(), view)
    }

    factory<ContainerRegisterContract.Presenter> { (view: ContainerRegisterContract.View) ->
        ContainerRegisterPresenter(inAERepository = get() , view)
    }
}