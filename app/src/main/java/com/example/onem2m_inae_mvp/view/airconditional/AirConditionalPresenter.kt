package com.example.onem2m_inae_mvp.view.airconditional

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.databinding.ActivityAirconditionalBinding
import com.example.onem2m_inae_mvp.repository.INAERepository

class AirConditionalPresenter(
    private val inAERepository: INAERepository,
    private val airConditionalView: AirConditionalContract.View,
) : AirConditionalContract.Presenter, BasePresenter() {
}