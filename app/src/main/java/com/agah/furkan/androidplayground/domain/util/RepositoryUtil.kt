package com.agah.furkan.androidplayground.domain.util

import com.agah.furkan.androidplayground.data.remote.model.response.BaseResponse
import com.agah.furkan.androidplayground.domain.Error
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <DATA, DOMAIN> suspendCall(
    coroutineContext: CoroutineContext,
    errorMapper: ErrorMapper,
    call: suspend () -> DATA?,
    map: (data: DATA) -> DOMAIN
): Result<DOMAIN> = withContext(coroutineContext) {
    return@withContext try {
        val result = call()
        if (result == null) {
            Result.Failure(Error.NullResponseError)
        } else {
            if ((result is BaseResponse) && result.isSuccess.not()) {
                Result.Failure(Error.NetworkError(result.message ?: Error.defaultMessage))
            } else {
                Result.Success(map(result))
            }
        }
    } catch (expected: Exception) {
        Result.Failure(errorMapper.mapError(expected.cause))
    }
}
