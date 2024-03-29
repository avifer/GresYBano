package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.repository.PublicationRepository
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsPublicationFavoriteUseCase @Inject constructor(
    private val repositoryPublication: PublicationRepository
) {

    operator fun invoke(idPublication: String): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryPublication.isPublicationFavorite(idPublication))
        }
    }

}