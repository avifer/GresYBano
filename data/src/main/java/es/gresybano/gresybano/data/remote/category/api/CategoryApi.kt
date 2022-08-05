package es.gresybano.gresybano.data.remote.category.api

import es.gresybano.gresybano.data.remote.category.model.CategoryDto
import retrofit2.http.GET

interface CategoryApi {

    @GET("c7c919a6-909b-4157-bc0f-68eeb7434ea7")    //TODO Cambiar por url final
    suspend fun getCategory(): CategoryDto?

    @GET("98c5788a-ad66-4c17-8fc8-4a451d9c9f4d")    //TODO Cambiar por url final
    suspend fun getTopCategories(): List<CategoryDto?>?

    @GET("98c5788a-ad66-4c17-8fc8-4a451d9c9f4d")    //TODO Cambiar por url final
    suspend fun getAllCategories(): List<CategoryDto?>?

    @GET("997b3055-314e-4a24-b1c5-a701bd048a9f")    //TODO Cambiar por url final
    suspend fun getAllCategoriesFull(): List<CategoryDto?>?

}