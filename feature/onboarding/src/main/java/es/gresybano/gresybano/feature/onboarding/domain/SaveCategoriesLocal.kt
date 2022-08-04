package es.gresybano.gresybano.feature.onboarding.domain

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveCategoriesLocal @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(list: List<CategoryBo>): Flow<Response<List<Long>>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryCategory.saveCategoriesFavorites(list))
        }
    }

}