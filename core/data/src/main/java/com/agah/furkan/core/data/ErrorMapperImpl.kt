package com.agah.furkan.core.data

import com.agah.furkan.core.data.model.Error
import retrofit2.HttpException


class ErrorMapperImpl : ErrorMapper {
    override fun mapError(throwable: Throwable?): Error {
        return when {
            throwable is HttpException && throwable.code() == 401 -> {
                Error.UnauthorizedError(throwable.message.orEmpty())
            }

            else -> {
                Error.CommonError(throwable?.message ?: "")
            }
        }
    }
}
