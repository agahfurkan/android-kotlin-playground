package com.agah.furkan.androidplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agah.furkan.androidplayground.ui.login.LoginFragmentVM
import com.agah.furkan.androidplayground.ui.main.MainFragmentVM
import com.agah.furkan.androidplayground.ui.productlist.ProductListFragmentVM
import com.agah.furkan.androidplayground.ui.register.RegisterFragmentVM
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
    abstract fun bindSplashFragmentVM(splashFragmentVM: SplashFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentVM::class)
    abstract fun bindLoginFragmentVM(loginFragmentVM: LoginFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterFragmentVM::class)
    abstract fun bindRegisterFragmentVM(registerFragmentVM: RegisterFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentVM::class)
    abstract fun bindMainFragmentVM(mainFragmentVM: MainFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductListFragmentVM::class)
    abstract fun bindProductListFragmentVM(productListFragmentVM: ProductListFragmentVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
