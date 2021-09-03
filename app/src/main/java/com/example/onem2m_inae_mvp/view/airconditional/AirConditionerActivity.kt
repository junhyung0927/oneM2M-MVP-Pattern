package com.example.onem2m_inae_mvp.view.airconditional

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContentInstanceMqttData
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityAirconditionerBinding
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.inae.INAEActivity
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.KEY_CONTAINER_DATA

import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AirConditionerActivity : BaseActivity<ActivityAirconditionerBinding>(),
    AirConditionerContract.View {
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override val presenter: AirConditionerPresenter by inject { parametersOf(this, mqttManager) }

    private val containerItem by lazy {
        val intent = intent
        intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
    }

    override val layoutId: Int
        get() = R.layout.activity_airconditioner

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityAirconditionerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.getChildResourceInfo()
    }

    override fun showChildResourceInfo(responseCntUril: ResponseCntUril) {
        containerResourceName = presenter.getResourceName(responseCntUril)
        presenter.apply {
            createSubscription(containerResourceName)
            connectMqtt(containerResourceName)
            getContainerInfo()
        }
    }

    override fun showMqttData(contentData: ContentInstanceMqttData) {
        binding.apply {
            sensingDataLoadingAnimationAirConditionerActivity.visibility = View.GONE
            sensingDataTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerItemImageViewAirConditionerActivity.visibility = View.VISIBLE
            scrollViewAirConditionerActivity.visibility = View.VISIBLE
            airConditionerDeleteAppCompactToggleButton.visibility = View.VISIBLE
            sensingDataHintTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerNameTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerItemImageViewAirConditionerActivity.setImageResource(containerItem.containerImage)

            containerNameTextViewAirConditionerActivity.text = containerItem.containerInstanceName
            if (!contentData.con.equals("on") && !contentData.con.equals("off")) {
                sensingDataTextViewAirConditionerActivity.text = contentData.con
            }
        }
    }

    override fun controlContainer(responseCnt: ResponseCnt) {
        binding.apply {
            presenter.apply {
                if (containerResourceName.isNotEmpty()) {
                    airConditionerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        val content = if (isChecked) {
                            "on"
                        } else {
                            "off"
                        }
                        deviceControl(content, containerResourceName)
                    }
                }

                airConditionerSearchDataModeAppCompactButton.setOnClickListener {
                    getContentInstanceInfo(containerResourceName)
                }

                airConditionerDeleteAppCompactToggleButton.setOnClickListener {
                    deleteDatabaseContainer(containerItem.containerInstanceName)
                }
            }
        }

    }

    override fun showINAEActivity() {
        startActivity(Intent(this, INAEActivity::class.java))
    }

    override fun onStop() {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
        super.onStop()
    }
}
