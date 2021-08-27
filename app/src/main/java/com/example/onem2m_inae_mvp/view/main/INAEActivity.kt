package com.example.onem2m_inae_mvp.view.main

import android.os.Bundle
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

//Activity만 있는 경우라도 별도의 View가 있다고 가정한다(코드 통일성)
class INAEActivity : BaseActivity(), INAEContract.View {
    private val presenter: INAEContract.Presenter by inject { parametersOf(this) }

    companion object {
        const val KEY_CONTAINER_DATA: String = "containerItem"
        var APP_ID: String= ""
    }

    override val layoutId: Int
        get() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        presenter.apply {
            createAE()
            getAEInfo()
        }
    }

    override fun getAppId(aeInfo: ResponseAE) {
        APP_ID = aeInfo.m2m_ae.aei
    }


}