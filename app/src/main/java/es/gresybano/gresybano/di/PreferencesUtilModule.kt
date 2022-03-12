package es.gresybano.gresybano.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.common.util.PreferencesUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesUtilModule {

    @Singleton
    @Provides
    fun getDatabaseApp(@ApplicationContext context: Context): PreferencesUtil =
        PreferencesUtil(context)

}