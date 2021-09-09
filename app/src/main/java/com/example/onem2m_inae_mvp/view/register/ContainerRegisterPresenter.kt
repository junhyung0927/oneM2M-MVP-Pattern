package com.example.onem2m_inae_mvp.view.register

import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.OneM2MRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContainerRegisterPresenter(
    private val oneM2MRepository: OneM2MRepository,
    private val containerRegisterView: ContainerRegisterContract.View,
): ContainerRegisterContract.Presenter, BasePresenter() {
    override fun registerContainer(
        deviceImage: Int,
        deviceName: String,
        deviceType: DeviceType
    ) = launch {
        withContext(Dispatchers.IO) {
            handle { oneM2MRepository.registerContainerInstance(deviceName, deviceImage, deviceType) }
        }?.let {
            containerRegisterView.showINAEActivity()
        }
    }
}