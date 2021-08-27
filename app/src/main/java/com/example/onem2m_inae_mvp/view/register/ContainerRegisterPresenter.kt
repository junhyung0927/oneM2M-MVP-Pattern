package com.example.onem2m_inae_mvp.view.register

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.view.main.INAEContract

class ContainerRegisterPresenter(
    private val inAERepository: INAERepository,
    private val containerRegisterView: ContainerRegisterContract.View,
): ContainerRegisterContract.Presenter, BasePresenter() {
}