package com.example.onem2m_inae_mvp.view.ae

import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.base.BaseView

interface INAEContract {
    //view - presenter 연결 view 구현 / presenter 호출
    interface View: BaseView<INAEPresenter> {

    }

    //view - presenter 연결 , presenter 구현 / view 호출
    interface Presenter: BasePresenter {
        suspend fun createAE()
    }
}