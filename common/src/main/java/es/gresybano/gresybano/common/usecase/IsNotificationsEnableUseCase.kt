package es.gresybano.gresybano.common.usecase

import es.gresybano.gresybano.common.util.TAG_ALL_FIREBASE
import es.gresybano.gresybano.domain.category.repository.RepositoryCategory
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsNotificationsEnableUseCase @Inject constructor(
    private val repositoryCategory: RepositoryCategory
) {

    operator fun invoke(): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryCategory.existCategoryTag(TAG_ALL_FIREBASE))
        }
    }

}