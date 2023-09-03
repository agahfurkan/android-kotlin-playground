package com.agah.furkan.androidplayground.ui

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.ui.main.MainScreen
import com.agah.furkan.core.logging.Logger
import com.agah.furkan.core.remoteconfig.RemoteConfig
import com.agah.furkan.core.remoteconfig.variant.LauncherIcon
import com.agah.furkan.core.session.SessionListener
import com.agah.furkan.core.session.SessionManager
import com.agah.furkan.core.ui.theme.AppTheme
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

            val alias = when (launcherIcon) {
                LauncherIcon.Default -> DEFAULT_ALIAS
                LauncherIcon.Variant1 -> LAUNCHER_ALIAS1
                LauncherIcon.Variant2 -> LAUNCHER_ALIAS2
                LauncherIcon.Variant3 -> LAUNCHER_ALIAS3
            }

            changeLauncherIcon(alias)
        }

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    override fun sessionStarted() {
        // no-op
    }

    override fun sessionEnded() {
        sharedViewModel.sessionEnded()
    }

    private fun changeLauncherIcon(aliasComponentName: String) {
        val packageManager: PackageManager = packageManager
        val packageName = "com.agah.furkan.androidplayground"
        val activeAlias = getActiveAlias()
        if (activeAlias == "$packageName$aliasComponentName") return

        val component = ComponentName(this, "$packageName$aliasComponentName")
        packageManager.setComponentEnabledSetting(
            component,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP,
        )

        val component2 = ComponentName(this, "$activeAlias")
        packageManager.setComponentEnabledSetting(
            component2,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP,
        )
    }

    private fun getActiveAlias(): String? {
        val packageManager: PackageManager = packageManager
        return packageManager.getLaunchIntentForPackage(packageName)?.component?.className
    }

    companion object {
        private const val DEFAULT_ALIAS = ".DefaultAlias"
        private const val LAUNCHER_ALIAS1 = ".ActivityAlias1"
        private const val LAUNCHER_ALIAS2 = ".ActivityAlias2"
        private const val LAUNCHER_ALIAS3 = ".ActivityAlias3"
    }
}
