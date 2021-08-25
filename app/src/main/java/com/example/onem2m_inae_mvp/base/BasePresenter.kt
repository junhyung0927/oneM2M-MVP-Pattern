package com.example.onem2m_inae_mvp.base

import java.lang.Exception

//모든 Presenter에 영향을 받는 함수
interface BasePresenter {
    fun start()
    suspend fun <T> handle(call: suspend () -> T): T?
    open fun onError(e: Exception)
}