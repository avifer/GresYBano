package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.common.util.parseJSON
import es.gresybano.gresybano.common.util.parseToJSON
import es.gresybano.gresybano.data.preferences.notification.NotificationPreferencesDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.notification.repository.RepositoryNotification
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.defaultResponse
import es.gresybano.gresybano.domain.response.getData

class RepositoryNotificationImpl(
    private val notificationPreferencesDataSource: NotificationPreferencesDataSource
) : RepositoryNotification, BaseRepository() {

    //TODO Revisar implementacion

    override suspend fun getAllNotifications(): Response<List<MessageNotificationBo>> {
        return notificationPreferencesDataSource.getAllNotifications().defaultResponse {
            parseJSON(it, Array<MessageNotificationBo.NewPublicationBo>::class.java)?.toList() ?: listOf()
        }
    }

    override suspend fun saveNotification(notificationBo: MessageNotificationBo): Response<Boolean> {
        val listNotifications = notificationPreferencesDataSource.getAllNotifications().getData()
        val list = parseJSON(listNotifications, Array<MessageNotificationBo.NewPublicationBo>::class.java)?.toList()?.toMutableList()
            ?: mutableListOf()
        list.add(notificationBo as MessageNotificationBo.NewPublicationBo)
        val listP = parseToJSON(list)
        return notificationPreferencesDataSource.setAllNotifications(listP).defaultResponse {
            it ?: false
        }
    }

}