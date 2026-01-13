package com.agah.furkan.core.data

import com.agah.furkan.core.data.model.BaseResponse
import com.agah.furkan.core.domain.model.DomainError
import com.agah.furkan.core.domain.model.DomainResult
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


suspend fun <DATA, DOMAIN> suspendCall(
    coroutineContext: CoroutineContext,
    errorMapper: ErrorMapper,
    mapOnSuccess: (data: DATA) -> DOMAIN,
    call: suspend () -> DATA?
): DomainResult<DOMAIN> = withContext(coroutineContext) {
    return@withContext try {
        val result = call()
        if (result == null) {
            DomainResult.Failure(DomainError.InvalidData("Null response received"))
        } else {
            if ((result is BaseResponse) && result.isSuccess.not()) {
                DomainResult.Failure(DomainError.Server(result.message ?: DomainError.DEFAULT_MESSAGE))
            } else {
                DomainResult.Success(mapOnSuccess(result))
            }
        }
    } catch (expected: Exception) {
        DomainResult.Failure(errorMapper.mapError(expected))
    }
}
