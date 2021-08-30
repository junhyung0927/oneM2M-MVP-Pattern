package com.example.onem2m_inae_mvp.view.adapter

import com.example.onem2m_in_ae.model.ContainerInstance

interface AdapterContract {
    interface View {
        var onClickFunc: ((ContainerInstance) -> Unit)?

    }

    interface Model {
        fun submitList(list: List<ContainerInstance>?)
    }
}