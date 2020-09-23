package com.agah.furkan.androidplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agah.furkan.androidplayground.ui.detail.PokemonDetailScreenVM
import com.agah.furkan.androidplayground.ui.main.MainScreenVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenVM::class)
    abstract fun bindUserViewModel(mainScreenVM: MainScreenVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
