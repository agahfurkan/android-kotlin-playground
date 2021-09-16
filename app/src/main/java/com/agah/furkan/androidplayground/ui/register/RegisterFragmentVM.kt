package com.agah.furkan.androidplayground.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.data.web.model.response.UserRegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registerUserResponse = MutableLiveData<ApiResponse<UserRegisterResponse>>()
    val registerUserResponse: LiveData<ApiResponse<UserRegisterResponse>> get() = _registerUserResponse

    fun registerNewUser(userRegisterBody: UserRegisterBody) {
        viewModelScope.launch {
            val response = userRepository.registerNewUser(userRegisterBody)
            _registerUserResponse.postValue(response)
        }
    }
}
