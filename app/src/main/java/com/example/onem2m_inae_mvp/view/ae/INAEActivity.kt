package com.example.onem2m_inae_mvp.view.ae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onem2m_inae_mvp.R
import com.example.onem2m_inae_mvp.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

//Activity만 있는 경우라도 별도의 View가 있다고 가정한다(코드 통일성)
class INAEActivity : BaseActivity(), INAEContract.View {
    private val presenter: INAEContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.createAE()
    }
}