package com.example.onem2m_in_ae.model

import androidx.annotation.DrawableRes
import com.example.onem2m_inae_mvp.R

enum class ContainerType(@DrawableRes val resId: Int) {
    AIRCONDITIONAL(resId = R.drawable.airconditioner),
    AIRPURIFIER(resId = R.drawable.airpurifier),
    BOILER(resId = R.drawable.boiler)
}

