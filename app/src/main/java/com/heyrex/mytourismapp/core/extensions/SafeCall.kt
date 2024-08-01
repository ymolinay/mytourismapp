package com.heyrex.mytourismapp.core.extensions

import com.heyrex.mytourismapp.core.data.api.FailureFactory
import retrofit2.Response

/**
 * Llama de manera segura a una API y maneja los resultados.
 *
 * @param T El tipo de respuesta de la API.
 * @param R El tipo de datos transformados.
 * @param transform Función que transforma la respuesta de la API.
 * @param errorFactory Fábrica de errores para manejar códigos de error.
 * @return Un resultado que contiene los datos transformados o un error.
 */
fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorFactory: FailureFactory<R> = FailureFactory()
): Result<R> {
    val body = body()
    return when {
        isSuccessful && body != null -> Result.success(transform(body))
        else -> errorFactory.handleCode(code = code(), errorBody = errorBody())
    }
}