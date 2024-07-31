package com.heyrex.mytourismapp.features.detail.data.request

import com.google.gson.annotations.SerializedName

data class DetailTourismRequest(
    @SerializedName("_id")
    val id: String,
)