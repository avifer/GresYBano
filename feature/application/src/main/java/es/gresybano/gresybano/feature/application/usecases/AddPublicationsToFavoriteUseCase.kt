package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddPublicationsToFavoriteUseCase @Inject constructor(
    private val repositoryPublication: RepositoryPublication
) {

    operator fun invoke(list: List<PublicationBo>): Flow<Response<List<Long>>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryPublication.savePublicationsFavorites(list))
        }
    }

}