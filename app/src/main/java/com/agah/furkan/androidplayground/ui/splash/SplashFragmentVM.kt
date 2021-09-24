package com.agah.furkan.androidplayground.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _isTokenValid = MutableLiveData<Boolean>()
    val isTokenValid: LiveData<Boolean> get() = _isTokenValid

    init {
        viewModelScope.launch {
            listOf(async {
                delay(3000)
            }, async {
                if (SharedPrefUtil.getToken() != null) {
                    val response = userRepository.validateToken(
                        ValidateTokenBody(
                            token = SharedPrefUtil.getToken().toString()
                        )
                    )
                    if (((response is ApiSuccessResponse) && response.data.isSuccess).not()) {
                        SharedPrefUtil.clearAllData()
                    }
                }
            }).awaitAll()
            _isTokenValid.postValue(SharedPrefUtil.getToken() != null)
        }
    }
}
