package com.agah.furkan.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.session.SessionManager
import com.agah.furkan.domain.login.LoginUseCase
import com.agah.furkan.core.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginScreenVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logger: Logger,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginState = Channel<LoginUiState>(Channel.BUFFERED)
    val loginState = _loginState.receiveAsFlow()

    private val _uiEvent = Channel<LoginScreenEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    private fun login(password: String, username: String) {
        viewModelScope.launch {
            _loginState.send(LoginUiState.Loading)
            logger.i("Attempting login for user: $username")
            when (val result = loginUseCase(username = username, password = password)) {
                is Result.Success -> {
                    sessionManager.onLoginSuccess()
                    _loginState.send(LoginUiState.Success)
                }
                is Result.Failure -> {
                    _loginState.send(LoginUiState.Failure(failureMessage = result.error.errorMessage))
                }
            }
        }
    }


    fun onLoginBtnClicked() {
        if (username.isBlank())
            return
        if (password.isBlank())
            return
        login(username = username, password = password)
    }

    fun onRegisterButtonClicked() {
        viewModelScope.launch {
            _uiEvent.send(LoginScreenEvent.NavigateToRegisterScreen)
        }
    }
}

sealed class LoginUiState {
    object Success : LoginUiState()
    object Loading : LoginUiState()
    data class Failure(val failureMessage: String) : LoginUiState()
}

