package es.gresybano.gresybano.data.local.favoritepublication.datasource

import es.gresybano.gresybano.data.local.favoritepublication.dao.FavoritePublicationDao
import es.gresybano.gresybano.data.local.favoritepublication.model.FavoritePublicationDbo
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.response.Response

class FavoritePublicationLocalDataSourceImpl(
    private val favoritePublicationDao: FavoritePublicationDao
) : FavoritePublicationLocalDataSource {

    override suspend fun getFavoritesPublications(): Response<List<FavoritePublicationDbo?>?> {
        return safeLocalCall { favoritePublicationDao.getAll() }
    }

    override suspend fun isPublicationFavorite(idPublication: String): Response<Boolean> {
        return safeLocalCall { favoritePublicationDao.getPublication(idPublication) != null }
    }

    override suspend fun insertFavoritesPublications(list: List<FavoritePublicationDbo>): Response<List<Long?>?> {
        return safeLocalCall { favoritePublicationDao.insert(*list.toTypedArray()) }
    }

    override suspend fun deleteFavoritesPublications(list: List<FavoritePublicationDbo>): Response<Int?> {
        return safeLocalCall { favoritePublicationDao.delete(*list.toTypedArray()) }
    }

}