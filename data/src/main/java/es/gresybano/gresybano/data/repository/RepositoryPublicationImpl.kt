package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSource
import es.gresybano.gresybano.data.local.favoritepublication.model.toBo
import es.gresybano.gresybano.data.local.favoritepublication.model.toFavoritePublicationDbo
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.publication.entity.FavoritePublicationBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.defaultResponse

class RepositoryPublicationImpl(
    private val publicationRemoteDataSource: PublicationRemoteDataSource,
    private val favoritePublicationLocalDataSource: FavoritePublicationLocalDataSource,
) : RepositoryPublication, BaseRepository() {

    override suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getPublicationsOfCategory(idCategory)
            .defaultResponse { listPublication ->
                listPublication?.mapNotNull { it?.toBo() } ?: listOf()
            }
    }

    override suspend fun getMorePopularPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getMorePopular().defaultResponse { listPublication ->
            listPublication?.mapNotNull { it?.toBo() } ?: listOf()
        }
    }

    override suspend fun getLastPublishedPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getLastPublished().defaultResponse { listPublication ->
            listPublication?.mapNotNull { it?.toBo() } ?: listOf()
        }
    }

    override suspend fun getPublication(idPublication: Long): Response<PublicationBo?> {
        return publicationRemoteDataSource.getPublication(idPublication)
            .defaultResponse { it?.toBo() }
    }

    override suspend fun getAllPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getAllPublications()
            .defaultResponse { listPublications ->
                listPublications?.mapNotNull { it?.toBo() } ?: listOf()
            }
    }

    override suspend fun getAllPublicationsFavorites(): Response<List<FavoritePublicationBo>> {
        return favoritePublicationLocalDataSource.getFavoritesPublications()
            .defaultResponse { listPublications ->
                listPublications?.mapNotNull { it?.toBo() } ?: listOf()
            }
    }

    override suspend fun savePublicationsFavorites(list: List<PublicationBo>): Response<List<Long>> {
        return favoritePublicationLocalDataSource.insertFavoritesPublications(list.map { it.toFavoritePublicationDbo() })
            .defaultResponse { it?.filterNotNull() ?: listOf() }
    }

    override suspend fun removePublicationsFavorites(list: List<PublicationBo>): Response<Int> {
        return favoritePublicationLocalDataSource.deleteFavoritesPublications(list.map { it.toFavoritePublicationDbo() })
            .defaultResponse { it ?: ZERO }
    }

}