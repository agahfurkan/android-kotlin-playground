package com.agah.furkan.androidplayground.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registerUserResponse = MutableLiveData<Result<String>>()
    val registerUserResponse: LiveData<Result<String>> get() = _registerUserResponse

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    fun registerNewUser() {
        viewModelScope.launch {
            val response = userRepository.registerNewUser(
                UseCaseParams.UserRegisterParams(
                    username = username,
                    password = password
                )
            )
            _registerUserResponse.postValue(response)
        }
    }
}
