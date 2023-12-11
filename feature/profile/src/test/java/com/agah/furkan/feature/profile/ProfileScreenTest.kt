package com.agah.furkan.feature.profile

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun profileScreenTest() {
        paparazzi.snapshot {
            ProfileScreen(onLogoutButtonClicked = {}, onDownloadButtonClicked = {})
        }
    }
}