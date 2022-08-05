package es.gresybano.gresybano.domain.publication.repository

import es.gresybano.gresybano.domain.publication.entity.FavoritePublicationBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.response.Response

interface RepositoryPublication {

    suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>>

    suspend fun getMorePopularPublications(): Response<List<PublicationBo>>

    suspend fun getLastPublishedPublications(): Response<List<PublicationBo>>

    suspend fun getPublication(idPublication: Long): Response<PublicationBo?>

    suspend fun getAllPublications(): Response<List<PublicationBo>>

    suspend fun getAllPublicationsFavorites(): Response<List<FavoritePublicationBo>>

    suspend fun savePublicationsFavorites(list: List<PublicationBo>): Response<List<Long>>

    suspend fun removePublicationsFavorites(list: List<PublicationBo>): Response<Int>

}