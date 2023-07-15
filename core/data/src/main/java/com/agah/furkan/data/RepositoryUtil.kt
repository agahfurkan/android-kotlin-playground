package com.agah.furkan.data


import com.agah.furkan.data.model.Error
import com.agah.furkan.data.model.Result
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


suspend fun <DATA, DOMAIN> suspendCall(
    coroutineContext: CoroutineContext,
    errorMapper: ErrorMapper,
    mapOnSuccess: (data: DATA) -> DOMAIN,
    call: suspend () -> DATA?
): Result<DOMAIN> = withContext(coroutineContext) {
    return@withContext try {
        val result = call()
        if (result == null) {
            Result.Failure(Error.NullResponseError)
        } else {
            if ((result is com.agah.furkan.data.model.BaseResponse) && result.isSuccess.not()) {
                Result.Failure(Error.NetworkError(result.message ?: Error.defaultMessage))
            } else {
                Result.Success(mapOnSuccess(result))
            }
        }
    } catch (expected: Exception) {
        Result.Failure(errorMapper.mapError(expected.cause))
    }
}
