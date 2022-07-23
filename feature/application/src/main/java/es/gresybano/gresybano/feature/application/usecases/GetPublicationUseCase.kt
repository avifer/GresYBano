package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import es.gresybano.gresybano.domain.response.CodeExceptions
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicationUseCase @Inject constructor(
    private val repositoryPublication: RepositoryPublication,
) {

    operator fun invoke(idPublication: Long?): Flow<Response<PublicationBo?>> {
        return flow {
            idPublication?.let {
                emit(Response.Loading())
                emit(repositoryPublication.getPublication(idPublication))
            } ?: kotlin.run { emit(Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))) }
        }
    }

}