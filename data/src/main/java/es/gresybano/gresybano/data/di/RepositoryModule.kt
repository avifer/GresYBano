package es.gresybano.gresybano.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.gresybano.gresybano.data.local.favoritecategory.dao.FavoriteCategoryDao
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSource
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSourceImpl
import es.gresybano.gresybano.data.remote.category.api.CategoryApi
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSourceImpl
import es.gresybano.gresybano.data.remote.publication.api.PublicationApi
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSourceImpl
import es.gresybano.gresybano.data.repository.RepositoryCategoryImpl
import es.gresybano.gresybano.data.repository.RepositoryPublicationImpl
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getCategoryRemoteDataSource(categoryApi: CategoryApi): CategoryRemoteDataSource {
        return CategoryRemoteDataSourceImpl(categoryApi)
    }

    @Provides
    fun getRepositoryRemoteDataSource(favoriteCategoryDao: FavoriteCategoryDao): FavoriteCategoryLocalDataSource {
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

    @Provides
    fun getPublicationRemoteDataSource(publicationApi: PublicationApi): PublicationRemoteDataSource {
        return PublicationRemoteDataSourceImpl(publicationApi)
    }

    @Singleton
    @Provides
    fun getRepositoryPublicationImpl(
        publicationRemoteDataSource: PublicationRemoteDataSource,
    ): RepositoryPublication {
        return RepositoryPublicationImpl(publicationRemoteDataSource)
    }

}