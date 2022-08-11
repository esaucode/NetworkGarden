package com.esaudev.networkgarden.domain.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}