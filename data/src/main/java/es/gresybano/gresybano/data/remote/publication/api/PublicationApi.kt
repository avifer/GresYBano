package es.gresybano.gresybano.data.remote.publication.api

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import retrofit2.http.GET

interface PublicationApi {

    @GET("98fd3585-e9f5-4ab2-be0d-d5bb95b4e672")        //TODO Cambiar por url final
    suspend fun getPublicationsCategory(): List<PublicationDto?>?

    @GET("98fd3585-e9f5-4ab2-be0d-d5bb95b4e672")        //TODO Cambiar por url final
    suspend fun getLastPublished(): List<PublicationDto?>?

    @GET("98fd3585-e9f5-4ab2-be0d-d5bb95b4e672")        //TODO Cambiar por url final
    suspend fun getMorePopular(): List<PublicationDto?>?

    @GET("80fff6fe-ab13-4c42-bd87-31168d15cb27")        //TODO Cambiar por url final
    suspend fun getPublication(): PublicationDto?

    @GET("98fd3585-e9f5-4ab2-be0d-d5bb95b4e672")        //TODO Cambiar por url final
    suspend fun getAllPublications(): List<PublicationDto?>?

}