package es.gresybano.gresybano.common.usecase

import com.google.firebase.messaging.FirebaseMessaging
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.repository.CategoryRepository
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveCategoriesToFavoriteUseCase @Inject constructor(
    private val repositoryCategory: CategoryRepository
) {

    operator fun invoke(list: List<CategoryBo>): Flow<Response<Int>> {
        return flow {
            emit(Response.Loading())
            list.mapNotNull { it.tag }.forEach {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(it)
            }
            emit(repositoryCategory.removeCategoriesFavorites(list))
        }
    }

}