package com.agah.furkan.androidplayground.di

import android.app.Application
import androidx.room.Room
import com.agah.furkan.androidplayground.BuildConfig
import com.agah.furkan.androidplayground.data.local.AppDatabase
import com.agah.furkan.androidplayground.data.web.service.PokemonService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "pokemonDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(appDatabase: AppDatabase) = appDatabase.pokemonDao()
}
