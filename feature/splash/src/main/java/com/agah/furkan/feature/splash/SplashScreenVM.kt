package com.agah.furkan.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.user.UserRepository
import com.agah.furkan.data.user.remote.model.request.ValidateTokenBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashScreenVM @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) :
    ViewModel() {
    private val _isTokenValid = Channel<Boolean>(Channel.BUFFERED)
    val isTokenValid = _isTokenValid.receiveAsFlow()

    private val splashMinDelay = 3000L

    init {
        viewModelScope.launch {
            listOf(
                async {
                    delay(splashMinDelay)
                },
                async {
                    if (!userPreference.getToken().isNullOrEmpty()) {
                        val result = userRepository.validateToken(
                            ValidateTokenBody(
                                token = userPreference.getToken().toString()
                            )
                        )
                        if (result is Result.Failure) {
                            userPreference.clearAllData()
                        }
                    }
                }
            ).awaitAll()
            _isTokenValid.send(!userPreference.getTokenSync().isNullOrEmpty())
        }
    }
}
