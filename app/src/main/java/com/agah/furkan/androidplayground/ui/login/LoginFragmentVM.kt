package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.model.request.UserLoginParams
import com.agah.furkan.androidplayground.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    private val _loginState = MutableSharedFlow<LoginUseCase.UiState>()
    val loginState: SharedFlow<LoginUseCase.UiState> get() = _loginState

    var username: String? = null
    var password: String? = null

    private fun login(userLoginParams: UserLoginParams) {
        viewModelScope.launch {
            val state = loginUseCase(userLoginParams)
            state.collect {
                _loginState.emit(it)
            }
        }
    }

    fun onLoginBtnClicked() {
        if (username.isNullOrBlank())
            return
        if (password.isNullOrBlank())
            return
        login(UserLoginParams(username = username!!, password = password!!))
    }
}
