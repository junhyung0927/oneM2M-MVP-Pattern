package com.example.onem2m_inae_mvp.view.inae

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.base.BaseView
import kotlinx.coroutines.Job

interface INAEContract {
    interface View: BaseView<Presenter> {
        fun getAppId(aeInfo: ResponseAE)
        fun getDatabase(containerDatabase: List<ContainerInstance>)
        fun showSelectedContainerView(containerInstance: ContainerInstance)
    }

    interface Presenter {
        fun createAE(): Job
        fun getAEInfo(): Job
        fun getContainerDatabase(isClear: Boolean): Job
    }
}