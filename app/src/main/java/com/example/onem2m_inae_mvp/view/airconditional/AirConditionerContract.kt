package com.example.onem2m_inae_mvp.view.airconditional

import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BaseView
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import kotlinx.coroutines.Job

interface AirConditionerContract {
    interface View: BaseView<Presenter> {
        fun showMqttData(contentData: String)
        fun getChildResourceInfo(responseCntUril: ResponseCntUril)
    }

    interface Presenter {
        fun getMqttData(contentData: String)
        fun getChildResourceInfo(): Job
        fun getResourceName(responseCntUril: ResponseCntUril): String
        fun createSubscription(resourceName: String): Job
        fun connectMqtt(containerResourceName: String)
    }
}