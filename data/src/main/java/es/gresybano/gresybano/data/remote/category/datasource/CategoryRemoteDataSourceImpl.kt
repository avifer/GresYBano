package es.gresybano.gresybano.data.remote.category.datasource

import es.gresybano.gresybano.data.remote.category.api.CategoryApi
import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.data.utils.safeRemoteCall
import es.gresybano.gresybano.domain.entities.response.Response

class CategoryRemoteDataSourceImpl(
    private val categoryApi: CategoryApi
) : CategoryRemoteDataSource, BaseRepository() {

    override suspend fun getCategory(id: Long): Response<CategoryDto?> {
        return safeRemoteCall { categoryApi.getCategory(id) }
    }

    override suspend fun getTopCategories(): Response<List<CategoryDto?>?> {
        return safeRemoteCall { categoryApi.getTopCategories() }
    }

    override suspend fun getAllCategories(): Response<List<CategoryDto?>?> {
        return safeRemoteCall { categoryApi.getAllCategories() }
    }

}