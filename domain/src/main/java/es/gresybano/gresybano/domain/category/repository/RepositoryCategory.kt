package es.gresybano.gresybano.domain.category.repository

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.response.Response

interface RepositoryCategory {

    suspend fun getCategory(id: Long): Response<CategoryBo?>

    suspend fun getTopCategories(): Response<List<CategoryBo>>

    suspend fun getAllCategories(): Response<List<CategoryBo>>

    suspend fun getAllCategoriesFavorites(): Response<List<CategoryBo>>

    suspend fun saveCategoriesFavorites(list: List<CategoryBo>): Response<List<Long>>

}