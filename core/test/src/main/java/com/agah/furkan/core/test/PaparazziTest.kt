package com.agah.furkan.core.test

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.agah.furkan.core.ui.theme.AppTheme
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule

abstract class PaparazziTest(private val config: PaparazziConfig? = null) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        renderingMode = config?.renderMode ?: SessionParams.RenderingMode.SHRINK,
        showSystemUi = false,
        theme = config?.theme ?: "android:Theme.Material.NoActionBar.Fullscreen",
    )

    fun Paparazzi.snapshotWithTheme(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            AppTheme {
                Surface {
                    content.invoke()
                }
            }
        }
    }
}
