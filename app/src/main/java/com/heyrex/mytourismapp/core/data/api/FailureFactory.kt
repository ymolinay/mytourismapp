package com.heyrex.mytourismapp.core.data.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heyrex.mytourismapp.core.domain.RequestFailure
import okhttp3.ResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Fábrica de errores para manejar diferentes tipos de fallos en solicitudes.
 *
 * @param R El tipo de datos para el resultado de error.
 */
open class  FailureFactory <R> {

    // Maneja códigos de error y crea resultados de fallo.
    open fun handleCode(code: Int, errorBody: ResponseBody?) =
        Result.failure<R>(when (code) {
            400 -> createApiError(errorBody)
            else -> RequestFailure.ApiError()
        })

    // Maneja excepciones y crea resultados de fallo.
    open fun handleException(exception: Throwable) =
        Result.failure<R>(when (exception) {
            is UnknownHostException, is SocketTimeoutException -> RequestFailure.NoConnectionError
            else -> RequestFailure.UnknownError
        })

    // Crea un error de API a partir del cuerpo de la respuesta.
    private fun createApiError(responseBody: ResponseBody?): RequestFailure.ApiError {
        val newLine = "\n"
        try {
            responseBody?.let {
                val gson = Gson()
                val listType = object : TypeToken<List<ErrorResponse>>() {}.type
                val serverErrorList: List<ErrorResponse> = gson.fromJson(it.string(), listType)
                val stringBuffer = StringBuilder()
                val codeList = mutableListOf<Int>()
                serverErrorList.forEach { serverError ->
                    stringBuffer.append(serverError.message).append(newLine)
                    codeList.add(serverError.code)
                }
                return RequestFailure.ApiError(
                    code = codeList,
                    message = stringBuffer.toString().substringBeforeLast(newLine)
                )
            }
            return RequestFailure.ApiError()
        } catch (exception: Exception) {
            return RequestFailure.ApiError()
        }
    }
}