package com.agah.furkan.core.remoteconfig.variant

import com.agah.furkan.core.remoteconfig.RemoteConfigKey

sealed class LauncherIcon(val value: Long) : Variant(RemoteConfigKey.LAUNCHER_ICON) {
    object Default : LauncherIcon(0)
    object Variant1 : LauncherIcon(1)
    object Variant2 : LauncherIcon(2)
    object Variant3 : LauncherIcon(3)

    companion object {
        fun fromValue(value: Long): LauncherIcon = when (value) {
            0L -> Default
            1L -> Variant1
            2L -> Variant2
            3L -> Variant3
            else -> Default
        }
    }
}

fun Long.toLauncherIcon(): LauncherIcon = LauncherIcon.fromValue(this)