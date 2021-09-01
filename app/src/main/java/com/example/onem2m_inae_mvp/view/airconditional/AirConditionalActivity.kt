package com.example.onem2m_inae_mvp.view.airconditional

import android.os.Bundle
import android.view.LayoutInflater
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityAirconditionalBinding
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AirConditionalActivity : BaseActivity<ActivityAirconditionalBinding>(), AirConditionalContract.View  {
    override val presenter: AirConditionalPresenter by inject { parametersOf(this) }

    override val layoutId: Int
        get() = R.layout.activity_airconditional

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityAirconditionalBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}