package es.gresybano.gresybano.common.usecase

import com.google.firebase.messaging.FirebaseMessaging
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCategoriesToFavoriteUseCase @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(list: List<CategoryBo>): Flow<Response<List<Long>>> {
        return flow {
            emit(Response.Loading())
            list.mapNotNull { it.tag }.forEach {
                FirebaseMessaging.getInstance().subscribeToTopic(it)
            }
            emit(repositoryCategory.saveCategoriesFavorites(list))
        }
    }

}