package com.agah.furkan.androidplayground.data.domain

interface DomainModelConverter<T> {
    fun toDomainModel(): T
}
