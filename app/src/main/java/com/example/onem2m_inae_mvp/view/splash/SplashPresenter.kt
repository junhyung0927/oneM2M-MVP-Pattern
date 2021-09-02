package com.example.onem2m_inae_mvp.view.splash

import android.content.Intent
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.view.inae.INAEActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashPresenter(
    private val inSplashView: SplashContract.View
): SplashContract.Presenter, BasePresenter() {
    override fun loadingProgress() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000).also {
                inSplashView.showMainPage()
            }
        }
    }

}