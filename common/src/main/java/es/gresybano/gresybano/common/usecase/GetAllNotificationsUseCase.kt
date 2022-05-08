package es.gresybano.gresybano.common.usecase

import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNotificationsUseCase @Inject constructor(
    private val repositoryNotification: RepositoryNotification
) {

    operator fun invoke(): Flow<Response<List<MessageNotificationBo>>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryNotification.getAllNotifications())
        }
    }

}