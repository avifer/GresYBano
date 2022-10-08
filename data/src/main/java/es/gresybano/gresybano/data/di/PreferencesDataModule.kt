package es.gresybano.gresybano.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.preferences.PreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesDataModule {

    @Singleton
    @Provides
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

}