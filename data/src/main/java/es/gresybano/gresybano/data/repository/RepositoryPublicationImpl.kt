package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.remote.publication.datasource.PublicationRemoteDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import java.util.*

class RepositoryPublicationImpl(
    private val publicationRemoteDataSource: PublicationRemoteDataSource,
) : RepositoryPublication, BaseRepository() {

    //TODO MOCK
    private val exampleListPublications =
        listOf(
            PublicationBo(
                id = 0,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cuarto de baño",
                publishDate = Date(),
                favorite = true,
            ),
            PublicationBo(
                id = 1,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Patio interno",
                publishDate = Date(),
                favorite = true,
            ),
            PublicationBo(
                id = 2,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
            ),
            PublicationBo(
                id = 3,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
                favorite = true,
            ),
            PublicationBo(
                id = 4,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
                favorite = true,
            ),
            PublicationBo(
                id = 5,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
            ),
            PublicationBo(
                id = 6,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
            ),
            PublicationBo(
                id = 7,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
                favorite = true,
            ),
            PublicationBo(
                id = 8,
                listImages = listOf(
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                    "https://www.roca.es/rocalife/wp-content/uploads/2020/03/Roca_banos_modernos_def-1.jpg",
                ),
                category = "Cocina",
                publishDate = Date(),
            ),
        )

    override suspend fun getPublicationsOfCategory(idCategory: Long): Response<List<PublicationBo>> {
        //TODO MOCK
        return Response.Successful(exampleListPublications)
        /*
        return publicationRemoteDataSource.getMorePopular().defaultResponse { listPublication ->
            listPublication?.filterNotNull()?.map { it.toBo() } ?: listOf()
        }
         */
    }

    override suspend fun getMorePopularPublicationsRemote(): Response<List<PublicationBo>> {
        //TODO MOCK
        return Response.Successful(exampleListPublications)
        /*
        return publicationRemoteDataSource.getMorePopular().defaultResponse { listPublication ->
            listPublication?.filterNotNull()?.map { it.toBo() } ?: listOf()
        }
         */
    }

    override suspend fun getLastPublishedPublicationsRemote(): Response<List<PublicationBo>> {
        //TODO MOCK
        return Response.Successful(exampleListPublications)
        /*
        return publicationRemoteDataSource.getLastPublished().defaultResponse { listPublication ->
            listPublication?.filterNotNull()?.map { it.toBo() } ?: listOf()
        }
         */
    }
}