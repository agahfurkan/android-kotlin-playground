package com.agah.furkan.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.domain.user.TokenValidationResult
import com.agah.furkan.domain.user.ValidateTokenUseCase
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
    private val validateTokenUseCase: ValidateTokenUseCase
) :
    ViewModel() {
    private val _isTokenValid = Channel<Boolean>(Channel.BUFFERED)
    val isTokenValid = _isTokenValid.receiveAsFlow()

    private val splashMinDelay = 3000L

    init {
        viewModelScope.launch {
            var tokenValidationResult: TokenValidationResult = TokenValidationResult.NoToken
            listOf(
                async {
                    delay(splashMinDelay)
                },
                async {
                    tokenValidationResult = validateTokenUseCase()
                }
            ).awaitAll()
            _isTokenValid.send(tokenValidationResult is TokenValidationResult.Valid)
        }
    }
}
