package com.example.onem2m_inae_mvp.view.inae

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.view.adapter.ContainerRecyclerViewAdapter
import com.example.onem2m_inae_mvp.view.airconditional.AirConditionerActivity
import com.example.onem2m_inae_mvp.view.airpurifier.AirPurifierActivity
import com.example.onem2m_inae_mvp.view.boiler.BoilerActivity
import com.example.onem2m_inae_mvp.view.register.ContainerRegisterActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

//Activity만 있는 경우라도 별도의 View가 있다고 가정한다(코드 통일성)
class INAEActivity : BaseActivity<ActivityMainBinding>(), INAEContract.View {
    override val presenter: INAEPresenter by inject { parametersOf(this, adapter, adapter) }

    //INAEContract.Presenter
    private val adapter by lazy { ContainerRecyclerViewAdapter() }

    companion object {
        const val KEY_CONTAINER_DATA: String = "containerItem"
        var APP_ID: String = ""
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.apply {
            createAE()
            getAEInfo()
            getContainerDatabase(false)
        }

        binding.apply {
            viewpager2INAEActivity.adapter = adapter
            TabLayoutMediator(
                tabLayoutINAEActivity,
                viewpager2INAEActivity
            ) { tab, position -> }.attach()

            floatingButtonAddContainerINAEActivity.setOnClickListener {
                startActivity(Intent(this@INAEActivity, ContainerRegisterActivity::class.java))
            }
        }
    }

    override fun getAppId(aeInfo: ResponseAE) {
        APP_ID = aeInfo.m2m_ae.aei
    }

    override fun getDatabase(containerDatabase: List<ContainerInstance>) {
        println("테스트: ${containerDatabase}")
        if (containerDatabase.isNotEmpty()) {
            binding.apply {
                explainTextViewINAEActivity.visibility = View.GONE
                viewpager2INAEActivity.visibility = View.VISIBLE
            }
        }
    }

    override fun showSelectedContainerView(containerInstance: ContainerInstance) {
        val destinationActivity = when (containerInstance.deviceType) {
            DeviceType.AIRCONDITIONAL -> AirConditionerActivity::class.java
            DeviceType.AIRPURIFIER -> AirPurifierActivity::class.java
            else -> BoilerActivity::class.java
        }

        val intent = Intent(this@INAEActivity, destinationActivity)
        intent.putExtra(KEY_CONTAINER_DATA, containerInstance)
        startActivity(intent)
    }
}