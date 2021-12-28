package com.agah.furkan.androidplayground.domain

interface ErrorMapper {
    fun mapError(throwable: Throwable?): Error
}
