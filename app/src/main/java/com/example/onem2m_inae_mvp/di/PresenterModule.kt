package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.view.adapter.ContainerRecyclerViewAdapter
import com.example.onem2m_inae_mvp.view.main.INAEActivity
import com.example.onem2m_inae_mvp.view.main.INAEContract
import com.example.onem2m_inae_mvp.view.main.INAEPresenter
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterContract
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { (view: INAEContract.View, model: ContainerRecyclerViewAdapter, adapterView: ContainerRecyclerViewAdapter)->
        INAEPresenter(
            inAERepository = get(),
            view,
            model,
            adapterView
        )
    }

    factory { (view: ContainerRegisterContract.View) ->
        ContainerRegisterPresenter(inAERepository = get() , view)
    }
}