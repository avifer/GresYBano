package es.gresybano.gresybano.data.local.category.datasource

import es.gresybano.gresybano.data.local.category.model.CategoryDbo
import es.gresybano.gresybano.domain.entities.response.Response

interface CategoryLocalDataSource {

    suspend fun getAllCategories(): Response<List<CategoryDbo?>?>

    suspend fun getCategory(id: Long): Response<CategoryDbo?>

    suspend fun insertCategories(list: List<CategoryDbo>): Response<List<Long?>?>

    suspend fun insertWithReplaceCategories(list: List<CategoryDbo>): Response<List<Long?>?>

    suspend fun updateCategories(list: List<CategoryDbo>): Response<Int>

    suspend fun deleteCategories(list: List<CategoryDbo>): Response<Int>

}