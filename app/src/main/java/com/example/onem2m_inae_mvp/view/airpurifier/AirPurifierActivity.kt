package com.example.onem2m_inae_mvp.view.airpurifier

import android.os.Bundle
import android.view.LayoutInflater
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityAirpurifierBinding
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AirPurifierActivity : BaseActivity<ActivityAirpurifierBinding>(), AirPurifierContract.View {
    override val presenter: INAEPresenter by inject { parametersOf(this) }

    override val layoutId: Int
        get() = R.layout.activity_airpurifier

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityAirpurifierBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
