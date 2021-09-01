package com.example.onem2m_inae_mvp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.onem2m_inae_mvp.view.main.INAEContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    abstract val presenter: BasePresenter
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    abstract val layoutId: Int

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClear()
    }
}