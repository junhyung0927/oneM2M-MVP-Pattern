package com.example.onem2m_inae_mvp.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

//모든 Presenter에 영향을 받는 함수
abstract class BasePresenter : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main.immediate

    fun onClear() {
        job.cancel()
    }

    suspend fun <T> handle(call: suspend () -> T): T? {
        try {
            return call.invoke()
        } catch (e: Exception) {
            onError(e)
        }
        return null
    }

    fun onError(e: Exception) {
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    400 -> println("400: 잘못된 요청입니다.")
                    403 -> println("403: 접근 허용 거부입니다.")
                    404 -> println("404: 해당 url은 존재하지 않습니다.")
                    409 -> println("409: 이미 생성된 리소스가 있습니다.")
                    500 -> println("500: 서버 에러입니다.")
                }
            }
            else -> e.printStackTrace()
        }
    }
}