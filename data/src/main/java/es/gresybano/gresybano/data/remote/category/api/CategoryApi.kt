package es.gresybano.gresybano.data.remote.category.api

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import retrofit2.http.GET

interface CategoryApi {

    @GET("18e97f42-f44c-4bdc-910f-7bd6fd439169")    //TODO Cambiar por url final
    suspend fun getCategory(id: Long): CategoryDto?

    @GET("57e11ef5-4e24-48e9-a72f-1e58220f7263")    //TODO Cambiar por url final
    suspend fun getTopCategories(): List<CategoryDto?>?

    @GET("11bef40a-f554-4a1c-9e8c-89f3386bd1b5")    //TODO Cambiar por url final
    suspend fun getAllCategories(): List<CategoryDto?>?

}