package com.example.onem2m_inae_mvp.service.mqtt

import org.json.JSONObject

class MqttClientRequestParser {
    fun notificationJsonParse(message: String): String {
        val json = JSONObject(message)
        val responserqi = json.getString("rqi")

        return responserqi
    }
}