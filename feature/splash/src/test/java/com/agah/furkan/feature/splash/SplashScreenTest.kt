package com.agah.furkan.feature.splash

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Test

class SplashScreenTest {
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun SplashScreenTest() {
        paparazzi.snapshot {
            SplashScreen()
        }
    }
}
