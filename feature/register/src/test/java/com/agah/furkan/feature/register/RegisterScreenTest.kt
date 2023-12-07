package com.agah.furkan.feature.register

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Test

class RegisterScreenTest {
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun RegisterScreenTest() {
        paparazzi.snapshot {
            RegisterScreen(
                username = "Janice Dunlap",
                password = "habemus",
                onUsernameChanged = {},
                onPasswordChanged = {},
                onRegisterClick = {})
        }
    }
}