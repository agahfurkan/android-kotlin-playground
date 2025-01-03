package com.agah.furkan.feature.home

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class HomeScreenTest : PaparazziTest() {
    @Test
    fun homeScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            HomeScreen {
            }
        }
    }
}
