package es.gresybano.gresybano.data.remote.publication.api

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import retrofit2.http.GET

interface PublicationApi {

    @GET("4aea9194-55c0-49e9-ac0e-3b2b28df4bc1")        //TODO Cambiar por url final
    suspend fun getPublicationsCategory(): List<PublicationDto?>?

    @GET("b4ce78df-7390-4885-a26d-3d350b83728e")        //TODO Cambiar por url final
    suspend fun getLastPublished(): List<PublicationDto?>?

    @GET("ca9abdbf-ecfa-41f2-af2d-a35e0d609e08")        //TODO Cambiar por url final
    suspend fun getMorePopular(): List<PublicationDto?>?

    @GET("69b63740-0a31-4c1d-8d75-6af4286451e7")        //TODO Cambiar por url final
    suspend fun getPublication(): PublicationDto?

}