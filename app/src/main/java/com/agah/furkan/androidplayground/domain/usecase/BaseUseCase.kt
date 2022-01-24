package com.agah.furkan.androidplayground.domain.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in PARAMS, out STATE> {
    suspend operator fun invoke(params: PARAMS): Flow<STATE>
}
