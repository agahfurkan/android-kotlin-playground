package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.response.UserLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _loginResponse = MutableLiveData<ApiResponse<UserLoginResponse>>()
    val loginResponse: LiveData<ApiResponse<UserLoginResponse>> get() = _loginResponse

    fun login(userLoginBody: UserLoginBody) {
        viewModelScope.launch {
            val response = userRepository.loginUser(userLoginBody)
            _loginResponse.postValue(response)
        }
    }
}
