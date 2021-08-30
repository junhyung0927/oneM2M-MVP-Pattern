package com.example.onem2m_inae_mvp.view.register

import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.view.main.INAEContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContainerRegisterPresenter(
    private val inAERepository: INAERepository,
    private val containerRegisterView: ContainerRegisterContract.View,
): ContainerRegisterContract.Presenter, BasePresenter() {
    override fun registerContainer(
        containerImage: Int,
        containerName: String,
        containerType: ContainerType
    ) = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.registerContainerInstance(containerName, containerImage, containerType) }
        }?.let {
            containerRegisterView.showINAEActivity()
        }
    }
}