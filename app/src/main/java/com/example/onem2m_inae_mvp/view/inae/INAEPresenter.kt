package com.example.onem2m_inae_mvp.view.inae

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.repository.INAERepository
import com.example.onem2m_inae_mvp.view.adapter.AdapterContract
import kotlinx.coroutines.*

class INAEPresenter(
    private val inAERepository: INAERepository,
    private val inAEView: INAEContract.View,
    private val adapterModel: AdapterContract.Model,
    private val adapterView: AdapterContract.View
) : INAEContract.Presenter, BasePresenter() {
    lateinit var containerInstanceList: List<ContainerInstance>

    init {
        adapterView.onClickFunc = {
            onClickListener(it)
        }
    }

    override fun createAE() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.createAE() }
        }
    }

    override fun getAEInfo() = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.getAEInfo() }?.let {
                inAEView.getAppId(it)
            }
        }
    }

    override fun getContainerDatabase(isClear: Boolean) = launch {
        withContext(Dispatchers.IO) {
            handle { inAERepository.getContentInstanceDatabase() }?.let {
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