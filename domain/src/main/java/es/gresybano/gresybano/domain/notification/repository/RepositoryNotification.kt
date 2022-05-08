package es.gresybano.gresybano.domain.notification.repository

import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.response.Response

interface RepositoryNotification {

    suspend fun getAllNotifications(): Response<List<MessageNotificationBo>>

    suspend fun saveNotification(notificationBo: MessageNotificationBo): Response<Boolean>

}