package es.gresybano.gresybano.common.usecase

import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarkNotificationOpenedUseCase @Inject constructor(
    private val repositoryNotification: RepositoryNotification
) {

    operator fun invoke(idNotification: Long): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryNotification.markNotificationOpened(idNotification))
        }
    }

}