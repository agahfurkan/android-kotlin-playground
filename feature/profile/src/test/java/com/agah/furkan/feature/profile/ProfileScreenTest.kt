package com.agah.furkan.feature.profile

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {
    @get:Rule
    private val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun ProfileScreenTest() {
        paparazzi.snapshot {
            ProfileScreen(onLogoutButtonClicked = {}, onDownloadButtonClicked = {})
        }
    }
}