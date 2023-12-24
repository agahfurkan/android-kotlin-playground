package com.agah.furkan.feature.search

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class SearchScreenTest : PaparazziTest() {

    @Test
    fun searchScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            SearchScreen(onBackPress = {})
        }
    }
}
