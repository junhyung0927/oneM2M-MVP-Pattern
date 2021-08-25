package com.example.onem2m_inae_mvp.base

import java.lang.Exception

//모든 Presenter에 영향을 받는 함수
interface BasePresenter {
    suspend fun <T> handle(call: suspend () -> T): T?
    fun onError(e: Exception)
}