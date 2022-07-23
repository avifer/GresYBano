package es.gresybano.gresybano.common.usecase

import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import es.gresybano.gresybano.domain.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveNotificationUseCase @Inject constructor(
    private val repositoryNotification: RepositoryNotification
) {

    companion object {
        private const val MAX_ELEMENTS_IN_LIST = 15
    }

    operator fun invoke(newNotificationBo: MessageNotificationBo): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            with(repositoryNotification.getAllNotifications()) {
                when (this) {
                    is Response.Error -> emit(Response.Error(error))
                    is Response.Loading -> emit(Response.Loading(loading))
                    is Response.Successful -> {
                        with(data?.toMutableList() ?: mutableListOf()) {
                            sortByDescending { it.dateReceived }
                            while (size >= MAX_ELEMENTS_IN_LIST) {
                                removeLast()
                            }

                            add(newNotificationBo)
                            emit(repositoryNotification.saveAllNotification(this))
                        }
                    }
                }
            }
        }
    }

}