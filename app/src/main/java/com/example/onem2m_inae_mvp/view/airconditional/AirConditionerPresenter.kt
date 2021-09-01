package com.example.onem2m_inae_mvp.view.airconditional

import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AirConditionerPresenter(
    private val inAERepository: INAERepository,
    private val airConditionerView: AirConditionerContract.View,
) : AirConditionerContract.Presenter, BasePresenter() {
    lateinit var contentData: String

    override fun getMqttData(contentData: String) {
        airConditionerView.getContentData(contentData)
    }

    override fun getChildResourceInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.getChildResourceInfo()
            }?.let {
                airConditionerView.getChildResourceInfo(it)
            }
        }
    }

    override fun getResourceName(responseCntUril: ResponseCntUril): String {
        return responseCntUril.m2mUril
            .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
            .find { it.contains("tvoc") }!!
            .split("/").last()
    }

    override fun createSubscription(resourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.createSubscription(resourceName)
            }?.let {
                println("섭스크립션 성공")
            }
        }
    }


}