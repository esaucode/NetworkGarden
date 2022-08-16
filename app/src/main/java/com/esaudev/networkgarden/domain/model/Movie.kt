package com.esaudev.networkgarden.domain.model

import androidx.annotation.DrawableRes

data class Movie(
    val name: String,
    @DrawableRes val imageRes: Int
)
