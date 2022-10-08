package es.gresybano.gresybano.domain.notification.repository

import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.domain.response.Response

interface NotificationRepository {

    suspend fun getAllNotifications(): Response<List<MessageNotificationBo>>

    suspend fun saveAllNotification(listNotifications: List<MessageNotificationBo>): Response<Boolean>

    suspend fun saveNotification(notificationBo: MessageNotificationBo): Response<Boolean>

    suspend fun markNotificationOpened(idNotification: Long): Response<Boolean>

}