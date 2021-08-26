package com.example.onem2m_inae_mvp.view.ae

import androidx.lifecycle.LiveData
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.base.BaseView

interface INAEContract {
    //view - presenter 연결 view 구현 / presenter 호출
    interface View: BaseView<Presenter> {
        //view와 관련된 함수들

    }

    //view - presenter 연결 , presenter 구현 / view 호출
    interface Presenter: BasePresenter {
        fun createAE()
    }
}