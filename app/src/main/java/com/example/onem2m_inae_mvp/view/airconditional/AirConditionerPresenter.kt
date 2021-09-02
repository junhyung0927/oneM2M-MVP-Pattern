package com.example.onem2m_inae_mvp.view.airconditional

import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.service.mqtt.MqttClientRequest
import com.example.onem2m_inae_mvp.service.mqtt.MqttClientRequestParser
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.service.mqtt.MqttRepository
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception

class AirConditionerPresenter(
    private val inAERepository: INAERepository,
    private val airConditionerView: AirConditionerContract.View,
    private val mqttManager: MqttManager
) : AirConditionerContract.Presenter, BasePresenter() {

    override fun getChildResourceInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.getChildResourceInfo()
            }?.let {
                airConditionerView.showChildResourceInfo(it)
            }
        }
    }

    override fun getContainerInfo() = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.getContainerInfo()
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
                inAERepository.getContentInstanceInfo(containerResourceName)
            }?.let {
                println("장치 정보 가져오기")
            }
        }
    }

    override fun createSubscription(resourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.createSubscription(resourceName)
            }?.let {
                println("subscription 생성 완료")
            }
        }
    }

    override fun connectMqtt(containerResourceName: String) {
        try {
            mqttManager.mqttConnect(APP_ID, containerResourceName).let {
                 mqttManager.mqttClient.setCallback(object: MqttCallbackExtended {
                    override fun connectionLost(cause: Throwable?) {
                        println("연결 lost")
                    }

                    override fun messageArrived(topic: String?, message: MqttMessage?) {
                        println("message arrived: ${message.toString()}")

                        val contentData = MqttRepository().parseMessage(message)
                        airConditionerView.showMqttData(contentData)

                        val retrqi = MqttClientRequestParser().notificationJsonParse(message.toString())
                        val responseMessage = MqttClientRequest().notificationResponse(retrqi)
                        val res_message = MqttMessage(responseMessage.toByteArray())

                        try {
                            val resp_topic = "/oneM2M/resp/Mobius2/${APP_ID}_${containerResourceName}/json"
                            mqttManager.mqttClient.publish(resp_topic, res_message)
                        } catch (e: MqttException) {
                            e.printStackTrace()
                        }
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken?) {
                        println("deliveryComplete: ${token}")
                    }

                    override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                        println("MQTT 연결 성공")
                    }
                })
            }
        } catch (e: Exception) {
            println("mqtt 접속 에러: ${e.toString()}")
        }
    }

    override fun deviceControl(content: String, containerResourceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.deviceControl(content, containerResourceName)
            }?.let {
                println("장치 제어 성공")
            }
        }
    }

    override fun deleteDatabaseContainer(containerInstanceName: String) = launch {
        withContext(Dispatchers.IO) {
            handle {
                inAERepository.deleteDatabaseContainer(containerInstanceName)
            }?.let {
                println("장치 제거 성공")
                airConditionerView.showINAEActivity()
            }
        }
    }

}
