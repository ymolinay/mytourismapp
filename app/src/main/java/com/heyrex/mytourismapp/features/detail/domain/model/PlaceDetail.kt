package com.heyrex.mytourismapp.features.detail.domain.model

import com.heyrex.mytourismapp.features.detail.data.response.DetailDataAlbumTourismResponse
import com.heyrex.mytourismapp.features.detail.data.response.DetailDataGeoTourismResponse
import com.heyrex.mytourismapp.features.detail.data.response.DetailDataTourismResponse

data class PlaceDetail(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val geo: PlaceDetailGeo,
    val album: List<PlaceDetailAlbum>,
)

data class PlaceDetailGeo(
    val lat: String,
    val lng: String,
)

data class PlaceDetailAlbum(
    val name: String,
    val image: String,
)

fun DetailDataTourismResponse.toModel(): PlaceDetail {
    return PlaceDetail(
        id = id,
        title = title,
        description = description,
        image = image,
        geo = geo.toModel(),
        album = album.map { it.toModel() },
    )
}

fun DetailDataGeoTourismResponse.toModel(): PlaceDetailGeo {
    return PlaceDetailGeo(
        lat = lat,
        lng = lng,
    )
}

fun DetailDataAlbumTourismResponse.toModel(): PlaceDetailAlbum {
    return PlaceDetailAlbum(
        name = name,
        image = image,
    )
}