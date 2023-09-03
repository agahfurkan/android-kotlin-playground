package com.agah.furkan.core.data

import com.agah.furkan.core.data.model.Error

interface ErrorMapper {
    fun mapError(throwable: Throwable?): Error
}
