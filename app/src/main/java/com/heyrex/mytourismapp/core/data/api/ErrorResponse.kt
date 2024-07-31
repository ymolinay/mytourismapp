package com.heyrex.mytourismapp.core.data.api

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("msg")
    var message: String
)