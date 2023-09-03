package com.agah.furkan.core.remoteconfig.di

import com.agah.furkan.core.remoteconfig.RemoteConfig
import com.agah.furkan.core.remoteconfig.RemoteConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteConfigModule {
    @Binds
    abstract fun provideRemoteConfig(remoteConfig: RemoteConfigImpl): RemoteConfig
}
