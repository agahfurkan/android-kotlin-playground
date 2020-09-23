package com.agah.furkan.androidplayground.di

import com.agah.furkan.androidplayground.ui.detail.PokemonDetailScreenFragment
import com.agah.furkan.androidplayground.ui.main.MainScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainScreen(): MainScreenFragment

    @ContributesAndroidInjector
    abstract fun contributePokemonDetailScreenFragment(): PokemonDetailScreenFragment
}
