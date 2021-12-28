package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.mapper.toRequestModel
import com.agah.furkan.androidplayground.data.remote.service.UserService
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UserLoginParams
import com.agah.furkan.androidplayground.domain.model.request.UserRegisterParams
import com.agah.furkan.androidplayground.domain.model.request.ValidateTokenParams
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

    override suspend fun loginUser(userLoginParams: UserLoginParams): Result<LoginResult> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            call = { userService.loginUser(userLoginParams.toRequestModel()) },
            map = { it.toDomainModel() }
        )

    override suspend fun registerNewUser(userRegisterParams: UserRegisterParams): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            call = { userService.registerNewUser(userRegisterParams.toRequestModel()) },
            map = { it.message ?: "" }
        )

    override suspend fun validateToken(validateTokenParams: ValidateTokenParams): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            call = { userService.validateToken(validateTokenParams.toRequestModel()) },
            map = { it.message ?: "" }
        )
}
