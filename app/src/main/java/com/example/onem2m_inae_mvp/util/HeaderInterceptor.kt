package com.example.onem2m_inae_mvp.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain){
        val newRequest = request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("X-M2M-RI", "12345")
            .addHeader("X-M2M-Origin", "S")
            .build()

        proceed(newRequest)
    }
}