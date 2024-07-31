package com.heyrex.mytourismapp.features.list.data.response

import com.google.gson.annotations.SerializedName

data class ListTourismResponse(
    @SerializedName("data")
    val data: List<ItemTourismResponse>,
)

data class ItemTourismResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
)