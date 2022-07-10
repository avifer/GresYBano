package es.gresybano.gresybano.data.remote.publication.datasource

import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import es.gresybano.gresybano.domain.entities.response.Response

interface PublicationRemoteDataSource {

    suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationDto>>

    suspend fun getLastPublished(): Response<List<PublicationDto>>

    suspend fun getMorePopular(): Response<List<PublicationDto>>

}