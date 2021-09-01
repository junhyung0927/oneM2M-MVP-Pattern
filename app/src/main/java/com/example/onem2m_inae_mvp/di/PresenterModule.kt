package com.example.onem2m_inae_mvp.di

import com.example.onem2m_inae_mvp.view.adapter.ContainerRecyclerViewAdapter
import com.example.onem2m_inae_mvp.view.airconditional.AirConditionalContract
import com.example.onem2m_inae_mvp.view.airconditional.AirConditionalPresenter
import com.example.onem2m_inae_mvp.view.airpurifier.AirPurifierContract
import com.example.onem2m_inae_mvp.view.airpurifier.AirPurifierPresenter
import com.example.onem2m_inae_mvp.view.boiler.BoilerContract
import com.example.onem2m_inae_mvp.view.boiler.BoilerPresenter
import com.example.onem2m_inae_mvp.view.inae.INAEContract
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
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

    factory { (view: AirConditionalContract.View) ->
        AirConditionalPresenter(inAERepository = get(), view)
    }

    factory { (view: AirPurifierContract.View) ->
        AirPurifierPresenter(inAERepository = get(), view)
    }

    factory { (view: BoilerContract.View) ->
        BoilerPresenter(inAERepository = get(), view)
    }
}