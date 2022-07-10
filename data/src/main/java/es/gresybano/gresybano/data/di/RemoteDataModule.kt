package es.gresybano.gresybano.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.remote.category.api.CategoryApi
import es.gresybano.gresybano.data.remote.publication.api.PublicationApi
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
        private const val BASE_URL_FIREBASE = "https://mocki.io/v1/"    //TODO Cambiar por la final
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
    fun provideCategoryApi(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): CategoryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FIREBASE)
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()
            .create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun providePublicationApi(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): PublicationApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FIREBASE)
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()
            .create(PublicationApi::class.java)
    }

}