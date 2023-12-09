package com.agah.furkan.feature.login

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun LoginScreenTest() {
        paparazzi.snapshot {
            LoginScreen(
                username = "Will Schroeder",
                password = "viderer",
                onLoginSuccess = {},
                onRegisterClicked = {},
                onUsernameChanged = {},
                onPasswordChanged = {})
        }
    }
}