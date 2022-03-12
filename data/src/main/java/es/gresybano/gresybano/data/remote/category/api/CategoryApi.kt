package es.gresybano.gresybano.data.remote.category.api

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import retrofit2.http.GET

interface CategoryApi {
    //TODO
    @GET("")
    suspend fun getAllCategories(): List<CategoryDto?>?
}