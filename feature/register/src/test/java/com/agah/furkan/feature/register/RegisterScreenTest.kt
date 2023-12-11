package com.agah.furkan.feature.register

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun registerScreenTest() {
        paparazzi.snapshot {
            RegisterScreen(
                username = "Dion Grant",
                password = "ocurreret",
                onUsernameChanged = {},
                onPasswordChanged = {},
                onRegisterClick = {})
        }
    }
}