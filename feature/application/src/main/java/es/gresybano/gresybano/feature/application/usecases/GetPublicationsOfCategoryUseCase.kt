package es.gresybano.gresybano.feature.application.usecases

import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.domain.response.CodeExceptions
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.publication.repository.RepositoryPublication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicationsOfCategoryUseCase @Inject constructor(
    private val repositoryPublication: RepositoryPublication,
) {

    operator fun invoke(idCategory: Long?): Flow<Response<List<PublicationBo>>> {
        return flow {
            idCategory?.let {
                emit(Response.Loading())
                emit(repositoryPublication.getPublicationsOfCategory(idCategory))
            } ?: kotlin.run { emit(Response.Error(ExceptionInfo(CodeExceptions.UNKNOWN))) }
        }
    }

}