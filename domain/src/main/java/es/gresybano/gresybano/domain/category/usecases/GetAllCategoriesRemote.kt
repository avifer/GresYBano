package es.gresybano.gresybano.domain.category.usecases

import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.domain.entities.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesRemote @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(): Flow<Response<List<CategoryBo>>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryCategory.getAllCategoriesRemote())
        }
    }

}