package com.example.onem2m_inae_mvp.view.splash

import com.example.onem2m_inae_mvp.base.BaseView

interface SplashContract {
    interface View: BaseView<Presenter> {
        fun showMainPage()
    }

    interface Presenter {
        fun loadingProgress()
    }
}