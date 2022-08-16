package es.gresybano.gresybano.domain.category.repository

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.entity.FavoriteCategoryBo
import es.gresybano.gresybano.domain.response.Response

interface RepositoryCategory {

    suspend fun getCategory(id: String): Response<CategoryBo?>

    suspend fun getTopCategories(): Response<List<CategoryBo>>

    suspend fun getAllCategories(): Response<List<CategoryBo>>

    suspend fun getAllCategoriesFull(): Response<List<CategoryBo>>

    suspend fun getAllCategoriesFavorites(): Response<List<FavoriteCategoryBo>>

    suspend fun saveCategoriesFavorites(list: List<CategoryBo>): Response<List<Long>>

    suspend fun removeCategoriesFavorites(list: List<CategoryBo>): Response<Int>

    suspend fun existCategoryTag(tagCategory: String): Response<Boolean>

}