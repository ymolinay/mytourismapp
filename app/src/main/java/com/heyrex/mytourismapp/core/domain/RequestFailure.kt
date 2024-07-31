package com.heyrex.mytourismapp.core.domain

sealed class RequestFailure(message: String = "") : Throwable(message) {
    class ApiError(var code: List<Int> = ArrayList(), message: String = "") :
        RequestFailure(message)

    object NoConnectionError : RequestFailure()
    object UnknownError : RequestFailure()
}