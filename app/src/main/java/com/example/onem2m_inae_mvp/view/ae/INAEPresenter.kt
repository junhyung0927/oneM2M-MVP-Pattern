package com.example.onem2m_inae_mvp.view.ae

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.onem2m_inae_mvp.repository.INAERepository
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class INAEPresenter(
    private val inAERepository: INAERepository,
    private val inAEView: INAEContract.View
): INAEContract.Presenter {


    override suspend fun <T> handle(call: suspend () -> T): T? {
        try {
            return call.invoke()
        } catch (e: Exception) {
            onError(e)
        }
        return null
    }

    override fun onError(e: java.lang.Exception) {
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

    override fun createAE() = runBlocking {
        inAERepository.createAE()
    }



}