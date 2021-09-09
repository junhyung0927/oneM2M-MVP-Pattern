package com.example.onem2m_inae_mvp.view.inae

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.OneM2MRepository
import com.example.onem2m_inae_mvp.view.adapter.AdapterContract
import kotlinx.coroutines.*

class INAEPresenter(
    private val oneM2MRepository: OneM2MRepository,
    private val inAEView: INAEContract.View,
    private val adapterModel: AdapterContract.Model,
    private val adapterView: AdapterContract.View
) : INAEContract.Presenter, BasePresenter() {
    private var containerInstanceList: List<ContainerInstance> = listOf()

    init {
        adapterView.onClickFunc = {
            onClickListener(it)
        }
    }

    override fun createAE() = launch {
        withContext(Dispatchers.IO) {
            handle { oneM2MRepository.createAE() }
        }
    }

    override fun getAEInfo() = launch {
        withContext(Dispatchers.IO) {
            handle { oneM2MRepository.getAEInfo() }?.let {
                inAEView.getAppId(it)
            }
        }
    }

    override fun getContainerDatabase(isClear: Boolean) = launch {
        withContext(Dispatchers.IO) {
            handle { oneM2MRepository.getContentInstanceDatabase() }?.let {
                inAEView.getDatabase(it)

                if (isClear) {
                    adapterModel.clearItem()
                }
                containerInstanceList = it
            }
        }
        adapterModel.submitList(containerInstanceList)
    }

    private fun onClickListener(containerInstance: ContainerInstance) {
        inAEView.showSelectedContainerView(containerInstance)
    }
}