package com.example.onem2m_inae_mvp.view.boiler

import android.os.Bundle
import android.view.LayoutInflater
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityBoilerBinding
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class BoilerActivity : BaseActivity<ActivityBoilerBinding>(), BoilerContract.View {
    override val presenter: INAEPresenter by inject { parametersOf(this) }

    override val layoutId: Int
        get() = R.layout.activity_boiler

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityBoilerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
