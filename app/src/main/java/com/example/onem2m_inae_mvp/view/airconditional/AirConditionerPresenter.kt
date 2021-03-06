package com.example.onem2m_inae_mvp.view.airconditional

import android.os.SystemClock
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.OneM2MRepository
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.airconditional.AirConditionerActivity.Companion.startTime
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AirConditionerPresenter(
    private val oneM2MRepository: OneM2MRepository,
    private val airConditionerView: AirConditionerContract.View,
    private val mqttManager: MqttManager
) : AirConditionerContract.Presenter, BasePresenter() {

    override fun getChildResourceInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.getChildResourceInfo()
            }?.let {
                airConditionerView.showChildResourceInfo(it)
            }
        }
    }

    override fun getContainerInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.getContainerInfo()
            }?.let {
                airConditionerView.controlContainer(it)
            }
        }
    }

    override fun getResourceName(responseCntUril: ResponseCntUril): String {
        return responseCntUril.m2mUril
            .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
            .find { it.contains("airconditioner") }!!
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
                airConditionerView.showMqttData(mqttMessage)
            }
        }
    }

    override fun deviceControl(content: String, containerResourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                oneM2MRepository.deviceControl(content, containerResourceName)
            }?.let {
                val endTime: Long = SystemClock.elapsedRealtime()
                val interval = endTime - startTime
                println("장치 제어 걸린 시간: $interval")
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
                airConditionerView.showINAEActivity()
            }
        }
    }

    override fun unsubscribeContainer(containerResourceName: String) {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
    }
}
