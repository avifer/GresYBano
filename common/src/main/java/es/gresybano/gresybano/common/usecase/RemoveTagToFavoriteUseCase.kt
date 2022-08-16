package es.gresybano.gresybano.common.usecase

import com.google.firebase.messaging.FirebaseMessaging
import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.common.util.TAG_ALL_FIREBASE
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveTagToFavoriteUseCase @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(tag: String): Flow<Response<Int>> {
        return flow {
            emit(Response.Loading())
            FirebaseMessaging.getInstance().unsubscribeFromTopic(tag)
            emit(
                repositoryCategory.removeCategoriesFavorites(
                    listOf(
                        CategoryBo(
                            EMPTY_STRING,
                            TAG_ALL_FIREBASE,
                            EMPTY_STRING,
                            EMPTY_STRING
                        )
                    )
                )
            )
        }
    }

}