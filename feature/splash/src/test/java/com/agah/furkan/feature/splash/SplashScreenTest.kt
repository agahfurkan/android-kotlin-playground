package com.agah.furkan.feature.splash

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class SplashScreenTest : PaparazziTest() {

    @Test
    fun splashScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            SplashScreen()
        }
    }
}
