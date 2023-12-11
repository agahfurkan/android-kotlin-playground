package com.agah.furkan.feature.splash

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun splashScreenTest() {
        paparazzi.snapshot {
            SplashScreen()
        }
    }
}
