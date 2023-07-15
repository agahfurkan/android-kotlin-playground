package com.agah.furkan.androidplayground.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import com.agah.furkan.preferences.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) :
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
                    if (userPreference.getToken() != null) {
                        val result = userRepository.validateToken(
                            UseCaseParams.ValidateTokenParams(
                                token = userPreference.getToken().toString()
                            )
                        )
                        if (result is com.agah.furkan.data.model.Result.Failure) {
                            userPreference.clearAllData()
                        }
                    }
                }
            ).awaitAll()
            _isTokenValid.emit(userPreference.getToken() != null)
        }
    }
}
