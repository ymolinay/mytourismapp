package com.heyrex.mytourismapp.features.splash.domain.model

data class AppData(
    val maintenance: Boolean = false,
    val urlUpdate: String = "",
    val minVersion: String = "",
)