package es.gresybano.gresybano.data.remote.publication.api

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import retrofit2.http.GET

interface PublicationApi {
    //TODO
    @GET("")
    suspend fun getPublicationsCategory(idCategory: Long): List<PublicationDto?>?

    //TODO
    @GET("")
    suspend fun getLastPublished(): List<PublicationDto?>?

    //TODO
    @GET("")
    suspend fun getMorePopular(): List<PublicationDto?>?
}