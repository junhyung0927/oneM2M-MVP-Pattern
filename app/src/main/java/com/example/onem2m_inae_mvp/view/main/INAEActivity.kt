package com.example.onem2m_inae_mvp.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

//Activity만 있는 경우라도 별도의 View가 있다고 가정한다(코드 통일성)
class INAEActivity : BaseActivity<ActivityMainBinding>(), INAEContract.View {
    private val presenter: INAEContract.Presenter by inject { parametersOf(this) }

    companion object {
        const val KEY_CONTAINER_DATA: String = "containerItem"
        var APP_ID: String= ""
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.apply {
            createAE()
            getAEInfo()
        }

        binding.apply {
            floatingButtonAddContainerINAEActivity.setOnClickListener {
                startActivity(Intent(this@INAEActivity, ContainerRegisterActivity::class.java))
            }
        }
    }

    override fun getAppId(aeInfo: ResponseAE) {
        APP_ID = aeInfo.m2m_ae.aei
    }
}