package es.gresybano.gresybano.data.preferences.notification

import android.content.Context
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.response.Response

class NotificationPreferencesDataSourceImpl(
    private val context: Context
) : NotificationPreferencesDataSource {

    companion object {
        private const val KEY_PREFERENCES = "PREFERENCES_GRES_Y_BANO_NOTIFICATION"
        private const val KEY_LIST_NOTIFICATIONS = "KEY_LIST_NOTIFICATIONS"
    }

    override suspend fun getAllNotifications(): Response<String> {
        return safeLocalCall {
            context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
                ?.getString(KEY_LIST_NOTIFICATIONS, "") ?: ""
        }
    }

    override suspend fun setAllNotifications(listNotifications: String): Response<Boolean> {
        return safeLocalCall {
            context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
                ?.edit()?.putString(KEY_LIST_NOTIFICATIONS, listNotifications)?.apply()
            true
        }
    }

}