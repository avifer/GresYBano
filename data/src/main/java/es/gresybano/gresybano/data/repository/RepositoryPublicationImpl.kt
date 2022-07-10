package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.remote.publication.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.entities.response.defaultResponse
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication

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
}