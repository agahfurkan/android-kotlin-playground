package com.agah.furkan.androidplayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.ui.main.MainScreen
import com.agah.furkan.core.remoteconfig.RemoteConfig
import com.agah.furkan.core.remoteconfig.variant.LauncherIcon
import com.agah.furkan.core.session.SessionListener
import com.agah.furkan.core.session.SessionManager
import com.agah.furkan.logging.Logger
import com.agah.furkan.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), SessionListener {
    private val sharedViewModel by viewModels<SharedViewModel>()

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var remoteConfig: RemoteConfig

    @Inject
    lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager.addSessionListener(this)
        remoteConfig.onActivated { launcherIcon: LauncherIcon ->
            logger.i("LauncherIcon: $launcherIcon")
            /*when (launcherIcon) {
                LauncherIcon.Default -> TODO()
                LauncherIcon.Variant1 -> TODO()
                LauncherIcon.Variant2 -> TODO()
                LauncherIcon.Variant3 -> TODO()
                LauncherIcon.Variant4 -> TODO()
                LauncherIcon.Variant5 -> TODO()
            }*/
        }

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    override fun sessionStarted() {

    }

    override fun sessionEnded() {
        sharedViewModel.sessionEnded()
    }
}
