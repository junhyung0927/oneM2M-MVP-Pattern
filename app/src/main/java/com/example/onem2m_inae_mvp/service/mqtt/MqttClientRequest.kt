package com.example.onem2m_inae_mvp.service.mqtt

class MqttClientRequest {
    fun notificationResponse(response: String): String {
        val responseMessage =
            """
            {"rsc":"2000",
            "rqi":"$response",
            "pc":""}
            """.trimIndent()

        return responseMessage
    }
}