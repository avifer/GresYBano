package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.data.local.favoritepublication.datasource.FavoritePublicationLocalDataSource
import es.gresybano.gresybano.data.local.favoritepublication.model.toBo
import es.gresybano.gresybano.data.local.favoritepublication.model.toFavoritePublicationDbo
import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
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

    override suspend fun getPublicationsOfCategory(idCategory: String): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getPublicationsOfCategory(idCategory).defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getMorePopularPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getMorePopular().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getLastPublishedPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getLastPublished().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getPublication(idPublication: String): Response<PublicationBo?> {
        return publicationRemoteDataSource.getPublication(idPublication).defaultResponse {
            putFavoritesInPublication(it)
        }
    }

    override suspend fun getAllPublications(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getAllPublications().defaultResponse {
            putFavoritesInList(it)
        }
    }

    override suspend fun getAllPublicationsFavorites(): Response<List<FavoritePublicationBo>> {
        return favoritePublicationLocalDataSource.getFavoritesPublications().defaultResponse {
            it?.mapNotNull { favorite ->
                favorite?.toBo()
            } ?: listOf()
        }
    }

    override suspend fun isPublicationFavorite(idPublication: String): Response<Boolean> {
        return favoritePublicationLocalDataSource.isPublicationFavorite(idPublication)
    }

    override suspend fun savePublicationsFavorites(list: List<PublicationBo>): Response<List<Long>> {
        return favoritePublicationLocalDataSource.insertFavoritesPublications(list.map { it.toFavoritePublicationDbo() })
            .defaultResponse { it?.filterNotNull() ?: listOf() }
    }

    override suspend fun removePublicationsFavorites(list: List<PublicationBo>): Response<Int> {
        return favoritePublicationLocalDataSource.deleteFavoritesPublications(list.map { it.toFavoritePublicationDbo() })
            .defaultResponse { it ?: ZERO }
    }

    private suspend fun putFavoritesInList(list: List<PublicationDto?>?): List<PublicationBo> {
        val listPublications = list?.mapNotNull { it?.toBo() } ?: listOf()
        @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
        when (val favoritesPublications =
            favoritePublicationLocalDataSource.getFavoritesPublications()) {
            is Response.Successful -> {
                favoritesPublications.data?.forEach { publicationFavorite ->
                    publicationFavorite?.let {
                        listPublications.find { publicationFavorite.id == it.id }?.favorite = true
                    }
                }
            }
        }
        return listPublications
    }

    private suspend fun putFavoritesInPublication(publication: PublicationDto?): PublicationBo? {
        val publicationBo = publication?.toBo()
        @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
        when (val favoritesPublications =
            favoritePublicationLocalDataSource.getFavoritesPublications()) {
            is Response.Successful -> {
                publicationBo?.favorite =
                    favoritesPublications.data?.find { it?.id == publicationBo?.id } != null
            }
        }
        return publicationBo
    }

}