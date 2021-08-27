package com.example.onem2m_inae_mvp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    private lateinit var presenter: BasePresenter
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    abstract val layoutId: Int

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClear()
    }
}