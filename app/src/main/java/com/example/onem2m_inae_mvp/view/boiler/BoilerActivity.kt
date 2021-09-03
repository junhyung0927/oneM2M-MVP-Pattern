package com.example.onem2m_inae_mvp.view.boiler

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
import com.example.onem2m_inae_mvp.databinding.ActivityBoilerBinding
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.airconditional.AirConditionerActivity
import com.example.onem2m_inae_mvp.view.inae.INAEActivity
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.KEY_CONTAINER_DATA
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class BoilerActivity : BaseActivity<ActivityBoilerBinding>(), BoilerContract.View {
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override val presenter: BoilerPresenter by inject { parametersOf(this, mqttManager) }

    private val containerItem by lazy {
        val intent = intent
        intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
    }

    override val layoutId: Int
        get() = R.layout.activity_boiler

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityBoilerBinding.inflate(layoutInflater)

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
            sensingDataLoadingAnimationBoilerActivity.visibility = View.GONE
            sensingDataTextViewBoilerActivity.visibility = View.VISIBLE
            containerItemImageViewBoilerActivity.visibility = View.VISIBLE
            scrollViewBoilerActivity.visibility = View.VISIBLE
            boilerDeleteAppCompactToggleButton.visibility = View.VISIBLE
            sensingDataHintTextViewBoilerActivity.visibility = View.VISIBLE
            containerNameTextViewBoilerActivity.visibility = View.VISIBLE
            containerItemImageViewBoilerActivity.setImageResource(containerItem.containerImage)

            containerNameTextViewBoilerActivity.text = containerItem.containerInstanceName
            if (!contentData.con.equals("on") && !contentData.con.equals("off")) {
                sensingDataTextViewBoilerActivity.text = contentData.con
            }
        }
    }

    override fun controlContainer(responseCnt: ResponseCnt) {
        binding.apply {
            presenter.apply {
                if (containerResourceName.isNotEmpty()) {
                    boilerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        val content = if (isChecked) {
                            "on"
                        } else {
                            "off"
                        }
                        deviceControl(content, containerResourceName)
                    }
                }

                boilerSearchDataModeAppCompactButton.setOnClickListener {
                    getContentInstanceInfo(containerResourceName)
                }

                boilerDeleteAppCompactToggleButton.setOnClickListener {
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
