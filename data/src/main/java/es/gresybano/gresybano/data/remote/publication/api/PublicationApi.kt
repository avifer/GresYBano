package es.gresybano.gresybano.data.remote.publication.api

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicationApi {

    @GET("publicationsOfCategory/{idCategory}")
    suspend fun getPublicationsOfCategory(@Path("idCategory") idCategory: String): List<PublicationDto?>?

    @GET("lastPublications")
    suspend fun getLastPublished(): List<PublicationDto?>?

    @GET("publications")        //TODO Cambiar por url final
    suspend fun getMorePopular(): List<PublicationDto?>?

    @GET("publications/{idPublication}")
    suspend fun getPublication(@Path("idPublication") idCategory: String): PublicationDto?

    @GET("publications")
    suspend fun getAllPublications(): List<PublicationDto?>?

}