package com.agah.furkan.androidplayground.di

import android.app.Application
import androidx.room.Room
import com.agah.furkan.androidplayground.BuildConfig.BASE_URL
import com.agah.furkan.androidplayground.data.local.AppDatabase
import com.agah.furkan.androidplayground.data.local.dao.DummyDao
import com.agah.furkan.androidplayground.data.remote.RestConstants
import com.agah.furkan.androidplayground.data.remote.service.CartService
import com.agah.furkan.androidplayground.data.remote.service.CategoryService
import com.agah.furkan.androidplayground.data.remote.service.ProductService
import com.agah.furkan.androidplayground.data.remote.service.UserService
import com.agah.furkan.androidplayground.util.retrofit.CustomCallFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CustomCallFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Singleton
    @Provides
    fun provideCartService(retrofit: Retrofit): CartService =
        retrofit.create(CartService::class.java)

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "playgroundDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(RestConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RestConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideDummyDao(appDatabase: AppDatabase): DummyDao = appDatabase.dummyDao()
}
