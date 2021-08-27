package com.example.onem2m_inae_mvp.view.register

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity

class ContainerRegisterActivity() : BaseActivity(), ContainerRegisterContract.View {
    companion object {
        private var pos: Int = -1
        private val containerType = listOf(
            ContainerType.AIRCONDITIONAL,
            ContainerType.AIRPURIFIER,
            ContainerType.BOILER,
        )
    }

    override val layoutId: Int
        get() = R.layout.activity_container_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    override fun addTextChange() {

    }
}