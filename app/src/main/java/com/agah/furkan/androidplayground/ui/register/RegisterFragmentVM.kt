package com.agah.furkan.androidplayground.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UserRegisterParams
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registerUserResponse = MutableLiveData<Result<String>>()
    val registerUserResponse: LiveData<Result<String>> get() = _registerUserResponse

    fun registerNewUser(userRegisterParams: UserRegisterParams) {
        viewModelScope.launch {
            val response = userRepository.registerNewUser(userRegisterParams)
            _registerUserResponse.postValue(response)
        }
    }
}
