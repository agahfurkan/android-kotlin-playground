package com.agah.furkan.feature.search

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Test

class SearchScreenTest {
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun SearchScreenTest() {
        paparazzi.snapshot {
            SearchScreen(onBackPress = {})
        }
    }
}