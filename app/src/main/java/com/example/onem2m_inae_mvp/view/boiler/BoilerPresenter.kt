package com.example.onem2m_inae_mvp.view.boiler

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository

class BoilerPresenter(
    private val inAERepository: INAERepository,
    private val boilerView: BoilerContract.View
) : BoilerContract.Presenter, BasePresenter()  {

}