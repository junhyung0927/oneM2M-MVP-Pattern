package com.example.onem2m_inae_mvp.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}