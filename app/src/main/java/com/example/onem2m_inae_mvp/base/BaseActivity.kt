package com.example.onem2m_inae_mvp.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var presenter: BasePresenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClear()
    }

    abstract val layoutId: Int
}