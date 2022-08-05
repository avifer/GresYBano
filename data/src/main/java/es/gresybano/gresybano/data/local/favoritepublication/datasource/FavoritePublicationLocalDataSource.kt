package es.gresybano.gresybano.data.local.favoritepublication.datasource

import es.gresybano.gresybano.data.local.favoritepublication.model.FavoritePublicationDbo
import es.gresybano.gresybano.domain.response.Response

interface FavoritePublicationLocalDataSource {

    suspend fun getFavoritesPublications(): Response<List<FavoritePublicationDbo?>?>

    suspend fun isPublicationFavorite(idPublication: Long): Response<Boolean>

    suspend fun insertFavoritesPublications(list: List<FavoritePublicationDbo>): Response<List<Long?>?>

    suspend fun deleteFavoritesPublications(list: List<FavoritePublicationDbo>): Response<Int?>

}