package com.agah.furkan.core.remoteconfig

import com.agah.furkan.core.remoteconfig.variant.LauncherIcon

interface RemoteConfig {
    fun onActivated(block: (launcherIcon: LauncherIcon) -> Unit)
}