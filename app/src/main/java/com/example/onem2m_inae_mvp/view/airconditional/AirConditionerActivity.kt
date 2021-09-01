package com.example.onem2m_inae_mvp.view.airconditional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityAirconditionerBinding
import com.example.onem2m_inae_mvp.service.mqtt.MqttManager
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.APP_ID
import com.example.onem2m_inae_mvp.view.inae.INAEActivity.Companion.KEY_CONTAINER_DATA

import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AirConditionerActivity : BaseActivity<ActivityAirconditionerBinding>(), AirConditionerContract.View  {
    override val presenter: AirConditionerPresenter by inject { parametersOf(this) }

    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override val layoutId: Int
        get() = R.layout.activity_airconditioner

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityAirconditionerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance

        binding.apply {
            sensingDataLoadingAnimationAirConditionerActivity.visibility = View.GONE
            sensingDataTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerItemImageViewAirConditionerActivity.visibility = View.VISIBLE
            scrollViewAirConditionerActivity.visibility = View.VISIBLE
            airconditionerDeleteAppCompactToggleButton.visibility = View.VISIBLE
            sensingDataHintTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerNameTextViewAirConditionerActivity.visibility = View.VISIBLE
            containerItemImageViewAirConditionerActivity.setImageResource(containerItem.containerImage)

            presenter.apply {
                getChildResourceInfo()
            }
        }
    }

    override fun getContentData(contentData: String) {

    }

    override fun getChildResourceInfo(responseCntUril: ResponseCntUril) {
        containerResourceName = presenter.getResourceName(responseCntUril)
        presenter.createSubscription(containerResourceName)
        mqttManager.getMqttClient(APP_ID, containerResourceName)
    }

    override fun onStop() {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
        super.onStop()
    }
}