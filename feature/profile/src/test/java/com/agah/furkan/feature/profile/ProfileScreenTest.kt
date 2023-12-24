package com.agah.furkan.feature.profile

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class ProfileScreenTest : PaparazziTest() {
    @Test
    fun profileScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            ProfileScreen(onLogoutButtonClicked = {}, onDownloadButtonClicked = {})
        }
    }

    @Test
    fun profileScreenActionButtonSnapshotTest() {
        paparazzi.snapshotWithTheme {
            ProfileActionButton()
        }
    }
}
