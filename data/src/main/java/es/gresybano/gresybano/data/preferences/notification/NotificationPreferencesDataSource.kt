package es.gresybano.gresybano.data.preferences.notification

import es.gresybano.gresybano.domain.response.Response

interface NotificationPreferencesDataSource {

    suspend fun getAllNotifications(): Response<String>

    suspend fun setAllNotifications(listNotifications: String): Response<Boolean>

}