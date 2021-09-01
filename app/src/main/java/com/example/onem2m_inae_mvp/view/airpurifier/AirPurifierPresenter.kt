package com.example.onem2m_inae_mvp.view.airpurifier

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository

class AirPurifierPresenter(
    private val inAERepository: INAERepository,
    private val airPurifierView: AirPurifierContract.View
): AirPurifierContract.Presenter, BasePresenter() {

}