package com.example.onem2m_inae_mvp.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import com.example.onem2m_inae_mvp.base.BasePresenter
import com.example.onem2m_inae_mvp.databinding.ActivitySplashBinding
import com.example.onem2m_inae_mvp.view.inae.INAEActivity
import com.example.onem2m_inae_mvp.view.inae.INAEPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding>(), SplashContract.View {
    override val presenter: SplashPresenter by inject { parametersOf(this) }

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadingProgress()
    }

    override fun showMainPage() {
        startActivity(Intent(this@SplashActivity, INAEActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onClear()
        super.onDestroy()
    }
}