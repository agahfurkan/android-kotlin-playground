package com.agah.furkan.core.data

import com.agah.furkan.core.domain.model.DomainError

interface ErrorMapper {
    fun mapError(throwable: Throwable?): DomainError
}
