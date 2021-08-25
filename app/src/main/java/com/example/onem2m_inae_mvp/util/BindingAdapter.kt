package com.example.onem2m_inae_mvp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:containerImageSelectedId")
fun setContainerImageId(
    imageView: ImageView,
    containerId: Int,
) {
    Glide.with(imageView.context)
        .load(containerId)
        .into(imageView)
}