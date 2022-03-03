package es.gresybano.gresybano.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.remote.blockchain.api.ExchangeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    companion object {
        private const val REQUEST_TIMEOUT = 10L
        private const val BASE_URL_API_EXCHANGE = "https://api.blockchain.com/v3/exchange/"
    }

    @Provides
    @Singleton
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideExchangeApi(okHttpClient: OkHttpClient, gson: GsonConverterFactory): ExchangeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API_EXCHANGE)
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()
            .create(ExchangeApi::class.java)
    }

}