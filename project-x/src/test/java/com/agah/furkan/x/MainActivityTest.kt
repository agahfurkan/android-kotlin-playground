package com.agah.furkan.x

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "Theme.Material3.DayNight.NoActionBar"
    )

    @Test
    fun testMainActivity() {
        val cl = paparazzi.inflate<View>(R.layout.activity_main)
        paparazzi.snapshot(cl)
    }
}