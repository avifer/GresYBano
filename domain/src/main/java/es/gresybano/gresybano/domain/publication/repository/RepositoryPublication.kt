package es.gresybano.gresybano.domain.publication.repository

import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.domain.entities.response.Response

interface RepositoryPublication {

    suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>>

    suspend fun getMorePopularPublicationsRemote(): Response<List<PublicationBo>>

    suspend fun getLastPublishedPublicationsRemote(): Response<List<PublicationBo>>

}