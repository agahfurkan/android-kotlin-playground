package com.agah.furkan.user

import com.agah.furkan.data.ErrorMapper
import com.agah.furkan.user.remote.UserService
import com.agah.furkan.user.remote.model.request.UserLoginBody
import com.agah.furkan.user.remote.model.request.UserRegisterBody
import com.agah.furkan.user.remote.model.request.ValidateTokenBody
import com.agah.furkan.user.remote.model.response.UserLoginResponse
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

    override suspend fun loginUser(userLoginBody: UserLoginBody): com.agah.furkan.data.model.Result<UserLoginResponse> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it }
        ) { userService.loginUser(userLoginBody) }

    override suspend fun registerNewUser(userRegisterBody: UserRegisterBody): com.agah.furkan.data.model.Result<String> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.registerNewUser(userRegisterBody) }

    override suspend fun validateToken(validateTokenBody: ValidateTokenBody): com.agah.furkan.data.model.Result<String> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.validateToken(validateTokenBody) }
}
