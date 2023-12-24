package com.agah.furkan.x

import android.view.LayoutInflater
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.ui.components.SearchTextField
import com.agah.furkan.x.databinding.ActivityMainBinding
import com.agah.furkan.x.model.StoreListModel
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
        val view = DataBindingUtil.inflate<ActivityMainBinding>(
            LayoutInflater.from(paparazzi.context),
            R.layout.activity_main,
            null,
            false
        ).apply {
            rvStoreList.layoutManager = LinearLayoutManager(paparazzi.context)
            rvStoreList.adapter = StoreListAdapter().apply {
                submitList(
                    listOf(
                        StoreListModel(
                            id = 5762,
                            storeImgUrl = "https://www.google.com/#q=tristique"
                        ),
                        StoreListModel(
                            id = 5762,
                            storeImgUrl = "https://www.google.com/#q=tristique"
                        ),
                        StoreListModel(
                            id = 5762,
                            storeImgUrl = "https://www.google.com/#q=tristique"
                        ),
                        StoreListModel(
                            id = 5762,
                            storeImgUrl = "https://www.google.com/#q=tristique"
                        )
                    )
                )
            }
            searchView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    val searchText = "test"
                    AppTheme {
                        SearchTextField(value = searchText, onFocusChanged = {
                        }, onValueChange = {
                        })
                    }
                }
            }
        }

        paparazzi.snapshot(view.root)
    }
}
