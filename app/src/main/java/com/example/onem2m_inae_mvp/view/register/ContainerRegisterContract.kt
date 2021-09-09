package com.example.onem2m_inae_mvp.view.register

import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_inae_mvp.base.BaseView
import kotlinx.coroutines.Job

interface ContainerRegisterContract {
    interface View: BaseView<Presenter> {
        fun showINAEActivity()
    }

    interface Presenter {
        fun registerContainer(deviceImage: Int, deviceName: String, deviceType: DeviceType): Job
    }
}