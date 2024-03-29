package es.gresybano.gresybano.data.remote.publication.datasource

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import es.gresybano.gresybano.domain.response.Response

interface PublicationRemoteDataSource {

    suspend fun getPublicationsOfCategory(idCategory: String): Response<List<PublicationDto?>?>

    suspend fun getLastPublished(): Response<List<PublicationDto?>?>

    suspend fun getMorePopular(): Response<List<PublicationDto?>?>

    suspend fun getPublication(idPublication: String): Response<PublicationDto?>

    suspend fun getAllPublications(): Response<List<PublicationDto?>?>

}