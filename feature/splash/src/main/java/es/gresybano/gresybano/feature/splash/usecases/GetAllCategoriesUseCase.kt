package es.gresybano.gresybano.feature.splash.usecases

import es.gresybano.gresybano.domain.category.repository.CategoryRepository
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repositoryCategory: CategoryRepository
) {

    operator fun invoke(): Flow<Response<List<CategoryBo>>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryCategory.getAllCategories())
        }
    }

}