package com.agah.furkan.androidplayground.di

import com.agah.furkan.androidplayground.ui.login.LoginFragment
import com.agah.furkan.androidplayground.ui.main.MainFragment
import com.agah.furkan.androidplayground.ui.register.RegisterFragment
import com.agah.furkan.androidplayground.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}
