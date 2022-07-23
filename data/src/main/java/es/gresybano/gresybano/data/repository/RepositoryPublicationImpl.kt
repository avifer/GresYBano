package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.defaultResponse

class RepositoryPublicationImpl(
    private val publicationRemoteDataSource: PublicationRemoteDataSource,
) : RepositoryPublication, BaseRepository() {

    override suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getPublicationsOfCategory(idCategory)
            .defaultResponse { listPublication ->
                listPublication?.map { it.toBo() } ?: listOf()
            }
    }

    override suspend fun getMorePopularPublicationsRemote(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getMorePopular().defaultResponse { listPublication ->
            listPublication?.map { it.toBo() } ?: listOf()
        }
    }

    override suspend fun getLastPublishedPublicationsRemote(): Response<List<PublicationBo>> {
        return publicationRemoteDataSource.getLastPublished().defaultResponse { listPublication ->
            listPublication?.map { it.toBo() } ?: listOf()
        }
    }

    override suspend fun getPublication(idPublication: Long): Response<PublicationBo?> {
        return publicationRemoteDataSource.getPublication(idPublication)
            .defaultResponse { it?.toBo() }
    }

}