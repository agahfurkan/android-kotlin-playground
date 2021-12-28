package com.agah.furkan.androidplayground.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.ValidateTokenParams
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _isTokenValid = MutableSharedFlow<Boolean>()
    val isTokenValid: SharedFlow<Boolean> get() = _isTokenValid

    private val splashMinDelay = 3000L

    init {
        viewModelScope.launch {
            listOf(
                async {
                    delay(splashMinDelay)
                },
                async {
                    if (SharedPrefUtil.getToken() != null) {
                        val result = userRepository.validateToken(
                            ValidateTokenParams(token = SharedPrefUtil.getToken().toString())
                        )
                        if (result is Result.Failure) {
                            SharedPrefUtil.clearAllData()
                        }
                    }
                }
            ).awaitAll()
            _isTokenValid.emit(SharedPrefUtil.getToken() != null)
        }
    }
}
