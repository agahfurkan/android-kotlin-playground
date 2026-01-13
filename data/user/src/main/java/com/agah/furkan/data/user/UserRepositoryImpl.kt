package com.agah.furkan.data.user

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.user.remote.UserService
import com.agah.furkan.data.user.remote.model.request.UserLoginBody
import com.agah.furkan.data.user.remote.model.request.UserRegisterBody
import com.agah.furkan.data.user.remote.model.request.ValidateTokenBody
import com.agah.furkan.domain.user.LoginRequest
import com.agah.furkan.domain.user.LoginResponse
import com.agah.furkan.domain.user.RegisterRequest
import com.agah.furkan.domain.user.UserRepository
import com.agah.furkan.domain.user.ValidateTokenRequest
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

    override suspend fun loginUser(request: LoginRequest): DomainResult<LoginResponse> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = {
                LoginResponse(
                    token = it.token,
                    userId = it.userId,
                    message = it.message
                )
            }
        ) { userService.loginUser(UserLoginBody(password = request.password, username = request.username)) }

    override suspend fun registerNewUser(request: RegisterRequest): DomainResult<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.registerNewUser(UserRegisterBody(username = request.username, password = request.password)) }

    override suspend fun validateToken(request: ValidateTokenRequest): DomainResult<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) { userService.validateToken(ValidateTokenBody(token = request.token)) }
}
