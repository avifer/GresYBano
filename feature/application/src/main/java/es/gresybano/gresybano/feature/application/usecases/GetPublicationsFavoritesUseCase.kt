package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.PublicationRepository
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.getData
import es.gresybano.gresybano.domain.response.getError
import es.gresybano.gresybano.domain.response.isSuccessful
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicationsFavoritesUseCase @Inject constructor(
    private val repositoryPublication: PublicationRepository
) {

    operator fun invoke(): Flow<Response<List<PublicationBo>>> {
        return flow {
            emit(Response.Loading())
            val allPublications = repositoryPublication.getAllPublications()

            if (allPublications.isSuccessful()) {
                emit(Response.Successful(allPublications.getData()?.filter { it.favorite }))

            } else {
                allPublications.getError()?.let {
                    emit(Response.Error(it))
                }
            }
        }
    }

}