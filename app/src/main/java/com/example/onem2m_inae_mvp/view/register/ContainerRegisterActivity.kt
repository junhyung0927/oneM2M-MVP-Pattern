package com.example.onem2m_inae_mvp.view.register

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityContainerRegisterBinding

class ContainerRegisterActivity() : BaseActivity<ActivityContainerRegisterBinding>(), ContainerRegisterContract.View {
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

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityContainerRegisterBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

    }

    override fun addTextChange() {

    }

}