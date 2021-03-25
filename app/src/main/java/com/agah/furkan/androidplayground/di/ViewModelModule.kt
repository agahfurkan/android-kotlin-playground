package com.agah.furkan.androidplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agah.furkan.androidplayground.ui.login.LoginFragmentVM
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import com.agah.furkan.androidplayground.ui.splash.SplashFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashFragmentVM::class)
    abstract fun bindUserViewModel(splashFragmentVM: SplashFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentVM::class)
    abstract fun bindLoginFragmentVM(loginFragmentVM: LoginFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentVM::class)
    abstract fun bindMainFragmentVM(mainFragmentVM: MainFragmentVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
