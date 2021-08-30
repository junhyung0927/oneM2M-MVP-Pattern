package com.example.onem2m_inae_mvp.view.main

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.base.BaseView
import kotlinx.coroutines.Job

interface INAEContract {
    //view - presenter 연결 view 구현 / presenter 호출
    interface View: BaseView<Presenter> {
        //view와 관련된 함수들
        fun getAppId(aeInfo: ResponseAE)
        fun getDatabase(containerDatabase: List<ContainerInstance>)
    }

    //view - presenter 연결 , presenter 구현 / view 호출
    interface Presenter {
        fun createAE(): Job
        fun getAEInfo(): Job
        fun getContainerDatabase(): Job
    }
}