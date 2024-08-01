package com.heyrex.mytourismapp.core.domain

/**
 * Clase que representa diferentes tipos de fallos en solicitudes.
 *
 * @param message Mensaje de error.
 */
sealed class RequestFailure(message: String = "") : Throwable(message) {
    class ApiError(var code: List<Int> = ArrayList(), message: String = "") :
        RequestFailure(message)

    object NoConnectionError : RequestFailure()
    object UnknownError : RequestFailure()
}