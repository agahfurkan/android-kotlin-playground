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
                LauncherIcon.Default -> ALIAS_LIST[0]
                LauncherIcon.Variant1 -> ALIAS_LIST[1]
                LauncherIcon.Variant2 -> ALIAS_LIST[2]
                LauncherIcon.Variant3 -> ALIAS_LIST[3]
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

    }

    override fun sessionEnded() {
        sharedViewModel.sessionEnded()
    }

    // TODO: check app kill issue 
    private fun changeLauncherIcon(aliasComponentName: String) {
        val packageManager: PackageManager = packageManager
        val packageName = "com.agah.furkan.androidplayground"
        val activeAlias = getActiveAlias()
        if (activeAlias == "$packageName$aliasComponentName") return

        val component = ComponentName(this, "$packageName$aliasComponentName")
        packageManager.setComponentEnabledSetting(
            component,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val component2 = ComponentName(this, "$activeAlias")
        packageManager.setComponentEnabledSetting(
            component2,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun getActiveAlias(): String? {
        val packageManager: PackageManager = packageManager
        return packageManager.getLaunchIntentForPackage(packageName)?.component?.className
    }

    companion object {
        private val ALIAS_LIST = listOf<String>(
            ".DefaultAlias",
            ".ActivityAlias1",
            ".ActivityAlias2",
            ".ActivityAlias3"
        )
    }
}
