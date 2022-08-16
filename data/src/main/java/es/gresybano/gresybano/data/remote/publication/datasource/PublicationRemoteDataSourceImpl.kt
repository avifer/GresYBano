package es.gresybano.gresybano.data.remote.publication.datasource

import es.gresybano.gresybano.data.remote.publication.api.PublicationApi
import es.gresybano.gresybano.data.remote.publication.model.PublicationDto
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.data.utils.safeRemoteCall
import es.gresybano.gresybano.domain.response.Response

class PublicationRemoteDataSourceImpl(
    private val publicationApi: PublicationApi
) : PublicationRemoteDataSource, BaseRepository() {

    override suspend fun getPublicationsOfCategory(idCategory: String): Response<List<PublicationDto?>?> {
        return safeRemoteCall {
            publicationApi.getPublicationsCategory()?.filterNotNull()
                ?: listOf()   //TODO Añadir parametro cuando este la url
        }
    }

    override suspend fun getLastPublished(): Response<List<PublicationDto?>?> {
        return safeRemoteCall { publicationApi.getLastPublished()?.filterNotNull() ?: listOf() }
    }

    override suspend fun getMorePopular(): Response<List<PublicationDto?>?> {
        return safeRemoteCall { publicationApi.getMorePopular()?.filterNotNull() ?: listOf() }
    }

    override suspend fun getPublication(idPublication: String): Response<PublicationDto?> {
        return safeRemoteCall { publicationApi.getPublication(idPublication) }
    }

    override suspend fun getAllPublications(): Response<List<PublicationDto?>?> {
        return safeRemoteCall { publicationApi.getAllPublications()?.filterNotNull() ?: listOf() }
    }

}