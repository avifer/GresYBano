package es.gresybano.gresybano.data.remote.category.datasource

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import es.gresybano.gresybano.domain.entities.response.Response

interface CategoryRemoteDataSource {

    suspend fun getAllCategories(): Response<List<CategoryDto?>?>

}