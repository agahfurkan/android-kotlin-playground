package com.agah.furkan.core.data.di

import com.agah.furkan.core.session.SessionManager
import com.agah.furkan.core.data.Constants
import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.ErrorMapperImpl
import com.agah.furkan.core.data.retrofit.AuthInterceptor
import com.agah.furkan.core.data.retrofit.CustomCallFactory
import com.agah.furkan.core.data.retrofit.HeaderInterceptor
import com.agah.furkan.core.preferences.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideErrorMapper(): ErrorMapper {
        return ErrorMapperImpl()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(client: OkHttpClient, @Named("remoteUrl") url: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addCallAdapterFactory(CustomCallFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        userPreference: UserPreference,
        sessionManager: SessionManager
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) = Unit

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        val trustManagerFactory: TrustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> =
            trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            "Unexpected default trust managers:" + trustManagers.contentToString()
        }

        val trustManager =
            trustManagers[0] as X509TrustManager

        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(userPreference))
            .addInterceptor(AuthInterceptor(sessionManager))
            .addInterceptor(interceptor)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory, trustManager)
            .hostnameVerifier { hostname: String?, session: SSLSession? -> true }
            .build()
    }
}
