package com.heyrex.mytourismapp.core.extensions

import com.heyrex.mytourismapp.core.data.api.FailureFactory
import retrofit2.Response

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