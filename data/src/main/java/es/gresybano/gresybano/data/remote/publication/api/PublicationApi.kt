package es.gresybano.gresybano.data.remote.publication.api

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import retrofit2.http.GET

interface PublicationApi {

    @GET("7c97d153-96f5-4f10-9dbf-596e25237675")        //TODO Cambiar por url final
    suspend fun getPublicationsCategory(idCategory: Long): List<PublicationDto?>?

    @GET("b4ce78df-7390-4885-a26d-3d350b83728e")        //TODO Cambiar por url final
    suspend fun getLastPublished(): List<PublicationDto?>?

    @GET("ca9abdbf-ecfa-41f2-af2d-a35e0d609e08")        //TODO Cambiar por url final
    suspend fun getMorePopular(): List<PublicationDto?>?
}