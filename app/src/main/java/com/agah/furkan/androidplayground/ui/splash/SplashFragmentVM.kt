package com.agah.furkan.androidplayground.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.data.web.model.response.ValidateTokenResponse
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _isTokenValid = MutableLiveData<Boolean>()
    val isTokenValid: LiveData<Boolean> get() = _isTokenValid

    private var isTimerEnded = false
    private var tempResponse: ApiResponse<ValidateTokenResponse>? = null

    init {
        if (SharedPrefUtil.getToken() != null) {
            validateToken()
        }
        viewModelScope.launch {
            delay(3000)
            isTimerEnded = true
            checkServiceResponseAndTimer()
        }
    }

    private fun validateToken() {
        viewModelScope.launch {
            val response = userRepository.validateToken(
                ValidateTokenBody(
                    token = SharedPrefUtil.getToken().toString()
                )
            )
            tempResponse = response
            checkServiceResponseAndTimer()
        }
    }

    private fun checkServiceResponseAndTimer() {
        if (SharedPrefUtil.getToken() == null) {
            _isTokenValid.postValue(false)
        } else {
            if (isTimerEnded && tempResponse != null) {
                if (tempResponse is ApiErrorResponse || (tempResponse is ApiSuccessResponse && !(tempResponse as ApiSuccessResponse).data.isSuccess)) {
                    SharedPrefUtil.clearAllData()
                    _isTokenValid.postValue(false)
                } else {
                    _isTokenValid.postValue(true)
                }
            }
        }
    }
}
