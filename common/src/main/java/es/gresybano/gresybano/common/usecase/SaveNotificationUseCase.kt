package es.gresybano.gresybano.common.usecase

import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveNotificationUseCase @Inject constructor(
    private val repositoryNotification: RepositoryNotification
) {

    operator fun invoke(newNotificationBo: MessageNotificationBo): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            emit(repositoryNotification.saveNotification(newNotificationBo))
        }
    }

}