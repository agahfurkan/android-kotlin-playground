package com.agah.furkan.androidplayground.domain.usecase

import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in PARAMS : UseCaseParams, out STATE> {
    suspend operator fun invoke(params: PARAMS): Flow<STATE>
}
