package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.data.local.favoritecategory.datasource.FavoriteCategoryLocalDataSource
import es.gresybano.gresybano.data.local.favoritecategory.model.toBo
import es.gresybano.gresybano.data.local.favoritecategory.model.toFavoriteCategoryDbo
import es.gresybano.gresybano.data.remote.category.datasource.CategoryRemoteDataSource
import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import es.gresybano.gresybano.data.remote.category.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.entity.FavoriteCategoryBo
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.defaultResponse

class RepositoryCategoryImpl(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val favoriteCategoryLocalDataSource: FavoriteCategoryLocalDataSource,
) : RepositoryCategory, BaseRepository() {

    override suspend fun getCategory(id: Long): Response<CategoryBo?> {
        return categoryRemoteDataSource.getCategory(id).defaultResponse {
            putFavoritesInCategory(it)
        }
    }

    override suspend fun getTopCategories(): Response<List<CategoryBo>> {
        return categoryRemoteDataSource.getAllCategories().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getAllCategories(): Response<List<CategoryBo>> {
        return categoryRemoteDataSource.getAllCategories().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getAllCategoriesFull(): Response<List<CategoryBo>> {
        return categoryRemoteDataSource.getAllCategoriesFull().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getAllCategoriesFavorites(): Response<List<FavoriteCategoryBo>> {
        return favoriteCategoryLocalDataSource.getCategories().defaultResponse { listCategories ->
            listCategories?.mapNotNull { category -> category?.toBo() } ?: listOf()
        }
    }

    override suspend fun saveCategoriesFavorites(list: List<CategoryBo>): Response<List<Long>> {
        return favoriteCategoryLocalDataSource.insertListCategories(list.map { it.toFavoriteCategoryDbo() })
            .defaultResponse { it?.filterNotNull() ?: listOf() }
    }

    override suspend fun removeCategoriesFavorites(list: List<CategoryBo>): Response<Int> {
        return favoriteCategoryLocalDataSource.deleteCategories(list.map { it.toFavoriteCategoryDbo() })
            .defaultResponse { it ?: ZERO }
    }

    override suspend fun existCategoryTag(tagCategory: String): Response<Boolean> {
        return favoriteCategoryLocalDataSource.existCategoryTag(tagCategory)
    }

    private suspend fun putFavoritesInList(list: List<CategoryDto?>?): List<CategoryBo> {
        val listCategories = list?.mapNotNull { it?.toBo() } ?: listOf()
        @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
        when (val favoritesCategories = favoriteCategoryLocalDataSource.getCategories()) {
            is Response.Successful -> {
                favoritesCategories.data?.forEach { categoryFavorite ->
                    categoryFavorite?.let {
                        listCategories.find { categoryFavorite.id == it.id }?.isFavorite = true
                    }
                }
            }
        }
        return listCategories
    }

    private suspend fun putFavoritesInCategory(category: CategoryDto?): CategoryBo? {
        val categoryBo = category?.toBo()
        @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
        when (val favoritesCategories = favoriteCategoryLocalDataSource.getCategories()) {
            is Response.Successful -> {
                categoryBo?.isFavorite =
                    favoritesCategories.data?.find { it?.id == categoryBo?.id } != null
            }
        }
        return categoryBo
    }

}