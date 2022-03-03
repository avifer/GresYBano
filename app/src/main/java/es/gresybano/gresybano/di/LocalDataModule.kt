package es.gresybano.gresybano.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.local.DatabaseApp
import es.gresybano.gresybano.data.local.blockchain.dao.BlockchainDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DatabaseApp =
        Room.databaseBuilder(context, DatabaseApp::class.java, DatabaseApp.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideBlockchainDao(database: DatabaseApp):
            BlockchainDao = database.blockchainDao()

}