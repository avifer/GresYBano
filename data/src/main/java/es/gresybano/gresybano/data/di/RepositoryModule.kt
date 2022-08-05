package es.gresybano.gresybano.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSource
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSourceImpl
import es.gresybano.gresybano.data.local.favoritepublication.dao.FavoritePublicationDao
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSource
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSourceImpl
import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSource
import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSourceImpl
import es.gresybano.gresybano.data.remote.category.api.CategoryApi
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSourceImpl
import es.gresybano.gresybano.data.remote.publication.api.PublicationApi
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSourceImpl
import es.gresybano.gresybano.data.repository.RepositoryCategoryImpl
import es.gresybano.gresybano.data.repository.RepositoryNotificationImpl
import es.gresybano.gresybano.data.repository.RepositoryPublicationImpl
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
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
    fun getRepositoryCategoryImpl(
        categoryRemoteDataSource: CategoryRemoteDataSource,
        favoriteCategoryLocalDataSource: FavoriteCategoryLocalDataSource,
    ): RepositoryCategory {
        return RepositoryCategoryImpl(categoryRemoteDataSource, favoriteCategoryLocalDataSource)
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
    fun getRepositoryPublicationImpl(
        publicationRemoteDataSource: PublicationRemoteDataSource,
        favoritePublicationLocalDataSource: FavoritePublicationLocalDataSource,
    ): RepositoryPublication {
        return RepositoryPublicationImpl(
            publicationRemoteDataSource,
            favoritePublicationLocalDataSource
        )
    }

    //endregion

    //region NOTIFICATION

    @Provides
    fun getNotificationPreferencesDataSourceImpl(@ApplicationContext context: Context): NotificationPreferencesDataSource {
        return NotificationPreferencesDataSourceImpl(context)
    }

    @Singleton
    @Provides
    fun getRepositoryNotificationImpl(
        notificationPreferencesDataSource: NotificationPreferencesDataSource,
    ): RepositoryNotification {
        return RepositoryNotificationImpl(notificationPreferencesDataSource)
    }

    //endregion

}