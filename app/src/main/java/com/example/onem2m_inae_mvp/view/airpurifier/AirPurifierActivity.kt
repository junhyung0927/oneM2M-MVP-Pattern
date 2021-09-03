package com.example.onem2m_inae_mvp.view.airpurifier

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
import com.example.onem2m_inae_mvp.databinding.ActivityAirpurifierBinding
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.inae.INAEActivity
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AirPurifierActivity : BaseActivity<ActivityAirpurifierBinding>(), AirPurifierContract.View {
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override val presenter: AirPurifierPresenter by inject { parametersOf(this, mqttManager) }

    private val containerItem by lazy {
        val intent = intent
        intent.getSerializableExtra(INAEActivity.KEY_CONTAINER_DATA) as ContainerInstance
    }

    override val layoutId: Int
        get() = R.layout.activity_airpurifier

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityAirpurifierBinding.inflate(layoutInflater)

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
            sensingDataLoadingAnimationAirPurifierActivity.visibility = View.GONE
            sensingDataTextViewAirPurifierActivity.visibility = View.VISIBLE
            containerItemImageViewAirPurifierActivity.visibility = View.VISIBLE
            scrollViewAirPurifierActivity.visibility = View.VISIBLE
            airPurifierDeleteAppCompactToggleButton.visibility = View.VISIBLE
            sensingDataHintTextViewAirPurifierActivity.visibility = View.VISIBLE
            containerNameTextViewAirPurifierActivity.visibility = View.VISIBLE
            containerItemImageViewAirPurifierActivity.setImageResource(containerItem.containerImage)

            containerNameTextViewAirPurifierActivity.text = containerItem.containerInstanceName
            if (!contentData.con.equals("on") && !contentData.con.equals("off")) {
                sensingDataTextViewAirPurifierActivity.text = contentData.con
            }

        }
    }

    override fun controlContainer(responseCnt: ResponseCnt) {
        binding.apply {
            presenter.apply {
                if (containerResourceName.isNotEmpty()) {
                    airPurifierControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        val content = if (isChecked) {
                            "on"
                        } else {
                            "off"
                        }
                        deviceControl(content, containerResourceName)
                    }
                }

                airPurifierSearchDataModeAppCompactButton.setOnClickListener {
                    getContentInstanceInfo(containerResourceName)
                }

                airPurifierDeleteAppCompactToggleButton.setOnClickListener {
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
