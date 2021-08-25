package com.example.onem2m_inae_mvp.view.ae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.databinding.ActivityMainBinding

class INAEActivity : BaseActivity(), INAEContract.View {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private lateinit var inaePresenter: INAEContract.Presenter

    companion object {
        const val KEY_CONTAINER_DATA: String = "containerItem"
        var APP_ID: String= ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setPresenter(presenter: INAEPresenter) {
        inaePresenter = checkNotNull(presenter)
    }
}