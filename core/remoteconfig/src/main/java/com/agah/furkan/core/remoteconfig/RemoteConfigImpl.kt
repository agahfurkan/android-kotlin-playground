package com.agah.furkan.core.remoteconfig

import com.agah.furkan.core.remoteconfig.variant.LauncherIcon
import com.agah.furkan.core.remoteconfig.variant.toLauncherIcon
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject

class RemoteConfigImpl @Inject constructor() : RemoteConfig {
    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.firebase_remote_config_defaults)
    }

    override fun onActivated(block: (launcherIcon: LauncherIcon) -> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            block(remoteConfig.getLong(RemoteConfigKey.LAUNCHER_ICON).toLauncherIcon())
        }
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                remoteConfig.activate().addOnCompleteListener {
                    block(remoteConfig.getLong(RemoteConfigKey.LAUNCHER_ICON).toLauncherIcon())
                }
                /*if (configUpdate.updatedKeys.contains(RemoteConfigKey.LAUNCHER_ICON)) {

                }*/
            }

            override fun onError(error: FirebaseRemoteConfigException) {

            }
        })
    }
}