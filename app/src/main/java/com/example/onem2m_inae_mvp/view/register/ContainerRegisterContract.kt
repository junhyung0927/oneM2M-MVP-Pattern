package com.example.onem2m_inae_mvp.view.register

import com.example.onem2m_inae_mvp.base.BaseView

interface ContainerRegisterContract {
    interface View: BaseView<Presenter> {
        fun addTextChange()
    }

    interface Presenter {

    }
}