package com.example.onem2m_inae_mvp.view.ae

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.view.adapter.AdapterContract
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class INAEPresenter(
    private val inAERepository: INAERepository,
    private val inAEView: INAEContract.View,
) : INAEContract.Presenter, BasePresenter() {
    override fun createAE() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.createAE() }
        }
    }

    override fun getAEInfo() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.getAEInfo() }?.let {
                inAEView.showAEInfo(it)
            }
        }
    }
}