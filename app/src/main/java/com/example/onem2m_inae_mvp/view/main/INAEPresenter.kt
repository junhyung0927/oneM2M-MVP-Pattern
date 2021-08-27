package com.example.onem2m_inae_mvp.view.main

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import kotlinx.coroutines.*

class INAEPresenter(
    private val inAERepository: INAERepository,
    private val inAEView: INAEContract.View,
) : INAEContract.Presenter, BasePresenter() {
    override fun createAE() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.createAE() }
        }
    }

    override fun getAEInfo() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.getAEInfo() }?.let {
                inAEView.getAppId(it)
            }
        }
    }
}