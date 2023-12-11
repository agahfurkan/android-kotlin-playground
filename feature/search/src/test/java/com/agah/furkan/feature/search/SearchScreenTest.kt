package com.agah.furkan.feature.search

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun searchScreenTest() {
        paparazzi.snapshot {
            SearchScreen(onBackPress = {})
        }
    }
}