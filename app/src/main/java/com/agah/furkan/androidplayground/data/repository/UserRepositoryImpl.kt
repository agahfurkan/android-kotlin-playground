package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.mapper.toRequestModel
import com.agah.furkan.androidplayground.data.remote.service.UserService
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.model.result.LoginResult
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import com.agah.furkan.androidplayground.domain.util.suspendCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl(
    private val userService: UserService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : UserRepository {
    @Inject
    constructor(
        userService: UserService,
        errorMapper: ErrorMapper,
    ) : this(userService, errorMapper, Dispatchers.IO)

    override suspend fun loginUser(userLoginParams: UseCaseParams.UserLoginParams): Result<LoginResult> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.toDomainModel() }
        ) { userService.loginUser(userLoginParams.toRequestModel()) }

    override suspend fun registerNewUser(userRegisterParams: UseCaseParams.UserRegisterParams): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.registerNewUser(userRegisterParams.toRequestModel()) }

    override suspend fun validateToken(validateTokenParams: UseCaseParams.ValidateTokenParams): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.validateToken(validateTokenParams.toRequestModel()) }
}
