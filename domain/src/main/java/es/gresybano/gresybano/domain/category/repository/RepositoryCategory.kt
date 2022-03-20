package es.gresybano.gresybano.domain.category.repository

import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.domain.entities.response.Response

interface RepositoryCategory {

    suspend fun getAllCategoriesRemote(): Response<List<CategoryBo>>

    suspend fun getAllCategoriesLocal(): Response<List<CategoryBo>>

    suspend fun getCategoryRemote(id: Long): Response<CategoryBo?>

    suspend fun getCategoryLocal(id: Long): Response<CategoryBo?>

    suspend fun insertCategoriesLocal(list: List<CategoryBo>): Response<List<Long>>

    suspend fun insertWithReplaceCategoriesLocal(list: List<CategoryBo>): Response<List<Long>>

    suspend fun updateCategoriesLocal(list: List<CategoryBo>): Response<Int>

    suspend fun deleteCategoriesLocal(list: List<CategoryBo>): Response<Int>

}