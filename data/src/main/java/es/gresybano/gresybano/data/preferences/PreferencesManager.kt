package es.gresybano.gresybano.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PreferencesManager(
    context: Context
) {

    companion object {
        private const val KEY_PREFERENCES = "PREFERENCES_GRES_Y_BANO"
    }

    private val sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE)

    fun hasValue(key: String) = sharedPreferences?.contains(key) ?: false

    fun getValue(key: String, defaultValue: String) =
        sharedPreferences?.getString(key, defaultValue) ?: defaultValue

    fun <T> setValue(key: String, value: T) =
        sharedPreferences?.edit()?.putString(key, value.toString())?.commit() ?: false

}