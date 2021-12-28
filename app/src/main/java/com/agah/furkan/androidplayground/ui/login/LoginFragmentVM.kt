package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UserLoginParams
import com.agah.furkan.androidplayground.domain.model.result.LoginResult
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _loginResponse = MutableSharedFlow<Result<LoginResult>>()
    val loginResponse: SharedFlow<Result<LoginResult>> get() = _loginResponse
    var username: String? = null
    var password: String? = null

    private fun login(userLoginParams: UserLoginParams) {
        viewModelScope.launch {
            val response = userRepository.loginUser(userLoginParams)
            _loginResponse.emit(response)
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
