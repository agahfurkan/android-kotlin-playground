package com.agah.furkan.core.data

import com.agah.furkan.core.domain.model.DomainError
import retrofit2.HttpException
import java.net.UnknownHostException
import java.net.SocketTimeoutException


class ErrorMapperImpl : ErrorMapper {
    override fun mapError(throwable: Throwable?): DomainError {
        return when (throwable) {
            is HttpException -> when (throwable.code()) {
                401 -> DomainError.Unauthorized(throwable.message())
                404 -> DomainError.NotFound(throwable.message())
                in 500..599 -> DomainError.Server(throwable.message())
                else -> DomainError.Unknown(throwable.message())
            }
            is UnknownHostException -> DomainError.Network("No internet connection")
            is SocketTimeoutException -> DomainError.Network("Connection timed out")
            else -> DomainError.Unknown(throwable?.message ?: DomainError.DEFAULT_MESSAGE)
        }
    }
}
