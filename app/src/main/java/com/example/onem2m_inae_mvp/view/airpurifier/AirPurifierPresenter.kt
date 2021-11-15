package com.example.onem2m_inae_mvp.view.airpurifier

import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.OneM2MRepository
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AirPurifierPresenter(
    private val oneM2MRepository: OneM2MRepository,
    private val airPurifierView: AirPurifierContract.View,
    private val mqttManager: MqttManager
): AirPurifierContract.Presenter, BasePresenter() {

    override fun getChildResourceInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.getChildResourceInfo()
            }?.let {
                airPurifierView.showChildResourceInfo(it)
            }
        }
    }

    override fun getContainerInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.getContainerInfo()
            }?.let {
                airPurifierView.controlContainer(it)
            }
        }
    }

    override fun getResourceName(responseCntUril: ResponseCntUril): String {
        return responseCntUril.m2mUril
            .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
            .find { it.contains("airpurifer") }!!
            .split("/").last()
    }

    override fun getContentInstanceInfo(containerResourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.getContentInstanceInfo(containerResourceName)
            }?.let {
                println("장치 정보 가져오기")
            }
        }
    }

    override fun createSubscription(resourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.createSubscription(resourceName)
            }?.let {
                println("subscription 생성 완료")
            }
        }
    }

    override fun connectMqtt(containerResourceName: String) {
        mqttManager.apply {
            mqttConnect(containerResourceName)
            mqttCallBackExtend(containerResourceName) { mqttMessage ->
                airPurifierView.showMqttData(mqttMessage)
            }
        }
    }

    override fun deviceControl(content: String, containerResourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.deviceControl(content, containerResourceName)
            }?.let {
                println("장치 제어 성공")
            }
        }
    }

    override fun deleteDatabaseContainer(containerInstanceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.deleteDatabaseContainer(containerInstanceName)
            }?.let {
                println("장치 제거 성공")
                airPurifierView.showINAEActivity()
            }
        }
    }

    override fun unsubscribeContainer(containerResourceName: String) {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
    }

}