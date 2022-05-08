package es.gresybano.gresybano.data.remote.category.datasource

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import es.gresybano.gresybano.domain.response.Response

interface CategoryRemoteDataSource {

    suspend fun getCategory(id: Long): Response<CategoryDto?>

    suspend fun getTopCategories(): Response<List<CategoryDto?>?>

    suspend fun getAllCategories(): Response<List<CategoryDto?>?>

}