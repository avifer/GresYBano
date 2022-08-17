package es.gresybano.gresybano.data.remote.category.api

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {

    @GET("category/{id}")
    suspend fun getCategory(@Path("id") idCategory: String): CategoryDto?

    @GET("categories")      //TODO Cambiar por url final
    suspend fun getTopCategories(): List<CategoryDto?>?

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDto?>?

    @GET("categoriesFull")
    suspend fun getAllCategoriesFull(): List<CategoryDto?>?

}