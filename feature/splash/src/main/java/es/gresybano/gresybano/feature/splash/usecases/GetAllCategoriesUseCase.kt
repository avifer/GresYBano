package es.gresybano.gresybano.feature.splash.usecases

import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(): Flow<Response<List<CategoryBo>>> {
        return flow {
            emit(Response.Loading())
            with(repositoryCategory.getAllCategories()) {
                emit(
                    if (isSuccessful() && getData() != null) {
                        this

                    } else {
                        Response.Error(getError() ?: ExceptionInfo(CodeExceptions.UNKNOWN))
                    }
                )
            }
        }
    }

}