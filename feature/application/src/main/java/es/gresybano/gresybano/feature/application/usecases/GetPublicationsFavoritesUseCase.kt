package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.getData
import es.gresybano.gresybano.domain.response.getError
import es.gresybano.gresybano.domain.response.isSuccessful
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicationsFavoritesUseCase @Inject constructor(
    private val repositoryPublication: RepositoryPublication
) {

    operator fun invoke(): Flow<Response<List<PublicationBo>>> {
        return flow {
            emit(Response.Loading())
            val allPublications = repositoryPublication.getAllPublications()
            val publicationsFavorites = repositoryPublication.getAllPublicationsFavorites()

            if (allPublications.isSuccessful()
                && publicationsFavorites.isSuccessful()
            ) {
                val listIdFavorites = publicationsFavorites.getData()?.map { it.id } ?: listOf()
                val listPublications = allPublications.getData()?.filter {
                    listIdFavorites.contains(it.id)
                }
                listPublications?.forEach { it.favorite = true }
                emit(Response.Successful(listPublications))

            } else {
                allPublications.getError()?.let {
                    emit(Response.Error(it))
                }
                publicationsFavorites.getError()?.let {
                    emit(Response.Error(it))
                }
            }
        }
    }

}