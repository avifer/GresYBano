package es.gresybano.gresybano.data.preferences.notification

import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.data.preferences.PreferencesManager
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.response.Response

class NotificationPreferencesDataSourceImpl(
    private val preferencesManager: PreferencesManager
) : NotificationPreferencesDataSource {

    companion object {
        private const val KEY_LIST_NOTIFICATIONS = "KEY_LIST_NOTIFICATIONS"
    }

    override suspend fun getAllNotifications(): Response<String> {
        return safeLocalCall {
            preferencesManager.getValue(KEY_LIST_NOTIFICATIONS, EMPTY_STRING)
        }
    }

    override suspend fun setAllNotifications(listNotifications: String): Response<Boolean> {
        return safeLocalCall {
            preferencesManager.setValue(KEY_LIST_NOTIFICATIONS, listNotifications)
        }
    }

}