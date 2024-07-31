package com.heyrex.mytourismapp.features.detail.data.response

import com.google.gson.annotations.SerializedName

data class DetailTourismResponse(
    @SerializedName("data")
    val data: DetailDataTourismResponse,
)

data class DetailDataTourismResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("geo")
    val geo: DetailDataGeoTourismResponse,
    @SerializedName("album")
    val album: List<DetailDataAlbumTourismResponse>,
)

data class DetailDataGeoTourismResponse(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
)

data class DetailDataAlbumTourismResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("image")
        val image: String,
)