package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.domain.entities.response.Response
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicationsOfCategoryUseCase @Inject constructor(
    private val repositoryPublication: RepositoryPublication,
) {

    operator fun invoke(idCategory: Long): Flow<Response<List<PublicationBo>>> {
        return flow {
            emit(Response.Loading())
            //TODO MOCK
            Thread.sleep(1500)
            emit(repositoryPublication.getPublicationsOfCategory(idCategory))
        }
    }

}