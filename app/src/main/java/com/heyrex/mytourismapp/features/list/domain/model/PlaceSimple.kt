package com.heyrex.mytourismapp.features.list.domain.model

import com.heyrex.mytourismapp.features.list.data.response.ItemTourismResponse

data class PlaceSimple(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
)

fun ItemTourismResponse.toModel(): PlaceSimple {
    return PlaceSimple(
        id = id,
        title = title,
        description = description,
        image = image,
    )
}