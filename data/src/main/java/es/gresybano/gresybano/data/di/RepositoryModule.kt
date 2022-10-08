package es.gresybano.gresybano.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSource
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSourceImpl
import es.gresybano.gresybano.data.local.favoritepublication.dao.FavoritePublicationDao
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSource
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSourceImpl
import es.gresybano.gresybano.data.preferences.PreferencesManager
import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSource
import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSourceImpl
import es.gresybano.gresybano.data.preferences.splash.SplashPreferencesDataSource
import es.gresybano.gresybano.data.preferences.splash.SplashPreferencesDataSourceImpl
import es.gresybano.gresybano.data.remote.category.api.CategoryApi
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSourceImpl
import es.gresybano.gresybano.data.remote.publication.api.PublicationApi
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSourceImpl
import es.gresybano.gresybano.data.repository.CategoryRepositoryImpl
import es.gresybano.gresybano.data.repository.NotificationRepositoryImpl
import es.gresybano.gresybano.data.repository.PublicationImplRepository
import es.gresybano.gresybano.data.repository.SplashRepositoryImpl
import es.gresybano.gresybano.domain.category.repository.CategoryRepository
import es.gresybano.gresybano.domain.notification.repository.NotificationRepository
import es.gresybano.gresybano.domain.publication.repository.PublicationRepository
import es.gresybano.gresybano.domain.splash.repository.SplashRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    //region CATEGORY

    @Provides
    fun getCategoryRemoteDataSource(categoryApi: CategoryApi): CategoryRemoteDataSource {
        return CategoryRemoteDataSourceImpl(categoryApi)
    }

    @Provides
    fun getFavoriteCategoryLocalDataSource(favoriteCategoryDao: FavoriteCategoryDao): FavoriteCategoryLocalDataSource {
        return FavoriteCategoryLocalDataSourceImpl(favoriteCategoryDao)
    }

    @Singleton
    @Provides
    fun getCategoryRepository(
        categoryRemoteDataSource: CategoryRemoteDataSource,
        favoriteCategoryLocalDataSource: FavoriteCategoryLocalDataSource,
    ): CategoryRepository {
        return CategoryRepositoryImpl(categoryRemoteDataSource, favoriteCategoryLocalDataSource)
    }

    //endregion

    //region PUBLICATION

    @Provides
    fun getPublicationRemoteDataSource(publicationApi: PublicationApi): PublicationRemoteDataSource {
        return PublicationRemoteDataSourceImpl(publicationApi)
    }

    @Provides
    fun getFavoritePublicationLocalDataSource(favoritePublicationDao: FavoritePublicationDao): FavoritePublicationLocalDataSource {
        return FavoritePublicationLocalDataSourceImpl(favoritePublicationDao)
    }

    @Singleton
    @Provides
    fun getPublicationRepository(
        publicationRemoteDataSource: PublicationRemoteDataSource,
        favoritePublicationLocalDataSource: FavoritePublicationLocalDataSource,
    ): PublicationRepository {
        return PublicationImplRepository(
            publicationRemoteDataSource,
            favoritePublicationLocalDataSource
        )
    }

    //endregion

    //region NOTIFICATION

    @Provides
    fun getNotificationPreferencesDataSource(preferencesManager: PreferencesManager): NotificationPreferencesDataSource {
        return NotificationPreferencesDataSourceImpl(preferencesManager)
    }

    @Singleton
    @Provides
    fun getNotificationRepository(
        notificationPreferencesDataSource: NotificationPreferencesDataSource,
    ): NotificationRepository {
        return NotificationRepositoryImpl(notificationPreferencesDataSource)
    }

    //endregion

    //region SPLASH

    @Provides
    fun getSplashPreferencesDataSource(preferencesManager: PreferencesManager): SplashPreferencesDataSource {
        return SplashPreferencesDataSourceImpl(preferencesManager)
    }

    @Singleton
    @Provides
    fun getSplashRepository(
        splashPreferencesDataSource: SplashPreferencesDataSource,
    ): SplashRepository {
        return SplashRepositoryImpl(splashPreferencesDataSource)
    }

    //endregion

}