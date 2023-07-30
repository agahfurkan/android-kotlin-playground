package com.agah.furkan.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.domain.login.LoginUseCase
import com.agah.furkan.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginScreenVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logger: Logger
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginUseCase.UiState>()
    val loginState: SharedFlow<LoginUseCase.UiState> get() = _loginState

    private val _uiEvent = MutableSharedFlow<LoginScreenEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    private fun login(password: String, username: String) {
        viewModelScope.launch {
            val state = loginUseCase.login(password, username)
            state.collect {
                if (it is LoginUseCase.UiState.Loading) {
                    logger.i(state.toString())
                }
                _loginState.emit(it)
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
            _uiEvent.emit(LoginScreenEvent.NavigateToRegisterScreen)
        }
    }
}
