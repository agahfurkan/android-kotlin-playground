package com.agah.furkan.data

interface ErrorMapper {
    fun mapError(throwable: Throwable?): com.agah.furkan.data.model.Error
}
