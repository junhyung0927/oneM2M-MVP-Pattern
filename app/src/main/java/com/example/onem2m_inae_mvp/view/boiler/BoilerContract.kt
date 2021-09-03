package com.example.onem2m_inae_mvp.view.boiler

import com.example.onem2m_in_ae.model.ContentInstanceMqttData
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BaseView
import kotlinx.coroutines.Job

interface BoilerContract {
    interface View: BaseView<Presenter> {
        fun showMqttData(contentData: ContentInstanceMqttData)

        fun showChildResourceInfo(responseCntUril: ResponseCntUril)

        fun showINAEActivity()

        fun controlContainer(responseCnt: ResponseCnt)
    }

    interface Presenter {
        fun getChildResourceInfo(): Job

        fun getContainerInfo(): Job

        fun getResourceName(responseCntUril: ResponseCntUril): String

        fun getContentInstanceInfo(containerResourceName: String): Job

        fun createSubscription(resourceName: String): Job

        fun connectMqtt(containerResourceName: String)

        fun deviceControl(content: String, containerResourceName: String): Job

        fun deleteDatabaseContainer(containerInstanceName: String): Job

        fun unsubscribeContainer(containerResourceName: String)
    }
}