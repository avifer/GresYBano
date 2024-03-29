package es.gresybano.gresybano.domain.publication.repository

import es.gresybano.gresybano.domain.publication.entity.FavoritePublicationBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.response.Response

interface PublicationRepository {

    suspend fun getPublicationsOfCategory(idCategory: String): Response<List<PublicationBo>>

    suspend fun getMorePopularPublications(): Response<List<PublicationBo>>

    suspend fun getLastPublishedPublications(): Response<List<PublicationBo>>

    suspend fun getPublication(idPublication: String): Response<PublicationBo?>

    suspend fun getAllPublications(): Response<List<PublicationBo>>

    suspend fun getAllPublicationsFavorites(): Response<List<FavoritePublicationBo>>

    suspend fun isPublicationFavorite(idPublication: String): Response<Boolean>

    suspend fun savePublicationsFavorites(list: List<PublicationBo>): Response<List<Long>>

    suspend fun removePublicationsFavorites(list: List<PublicationBo>): Response<Int>

}