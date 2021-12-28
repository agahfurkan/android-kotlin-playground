package com.agah.furkan.androidplayground.data.mapper

import com.agah.furkan.androidplayground.domain.Error
import com.agah.furkan.androidplayground.domain.ErrorMapper
import javax.inject.Inject

class ErrorMapperImpl @Inject constructor() : ErrorMapper {
    override fun mapError(throwable: Throwable?): Error {
        return Error.CommonError(throwable?.message ?: "")
        /*return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    500 -> {

                    }
                    404 -> {

                    }
                }
            }
            is IOException -> {

            }
        }*/
    }
}
