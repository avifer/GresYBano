package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSource
import es.gresybano.gresybano.data.local.favoritecategory.model.toBo
import es.gresybano.gresybano.data.local.favoritecategory.model.toDbo
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.remote.category.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.entities.response.defaultResponse

class RepositoryCategoryImpl(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val favoriteCategoryLocalDataSource: FavoriteCategoryLocalDataSource,
) : RepositoryCategory, BaseRepository() {

    override suspend fun getCategory(id: Long): Response<CategoryBo?> {
        return categoryRemoteDataSource.getCategory(id).defaultResponse { it?.toBo() }
    }

    override suspend fun getTopCategories(): Response<List<CategoryBo>> {
        return categoryRemoteDataSource.getAllCategories().defaultResponse { listCategories ->
            listCategories?.mapNotNull { category -> category?.toBo() } ?: listOf()
        }
    }

    override suspend fun getAllCategories(): Response<List<CategoryBo>> {
        return categoryRemoteDataSource.getAllCategories().defaultResponse { listCategories ->
            listCategories?.mapNotNull { category -> category?.toBo() } ?: listOf()
        }
    }

    override suspend fun saveCategoriesFavorites(list: List<CategoryBo>): Response<List<Long>> {
        return favoriteCategoryLocalDataSource.insertListCategories(list.map { it.toDbo() })
            .defaultResponse { it?.filterNotNull() ?: listOf() }
    }

    override suspend fun getAllCategoriesFavorites(): Response<List<CategoryBo>> {
        return favoriteCategoryLocalDataSource.getCategories().defaultResponse { listCategories ->
            listCategories?.mapNotNull { category -> category?.toBo() } ?: listOf()
        }
    }

}