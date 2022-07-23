package es.gresybano.gresybano.domain.publication.repository

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.response.Response

interface RepositoryPublication {

    suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>>

    suspend fun getMorePopularPublicationsRemote(): Response<List<PublicationBo>>

    suspend fun getLastPublishedPublicationsRemote(): Response<List<PublicationBo>>

    suspend fun getPublication(idPublication: Long): Response<PublicationBo?>

}