package es.gresybano.gresybano.common.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PreferencesUtil(
    context: Context
) {

    companion object {
        private const val KEY_PREFERENCES = "PREFERENCES_GRES_Y_BANO"

        private const val KEY_IS_ON_BOARDING_CONFIG = "KEY_IS_ON_BOARDING_CONFIG"
    }

    private val sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE)

    fun getIsOnBoardingConfig(): Boolean {
        return sharedPreferences?.getBoolean(KEY_IS_ON_BOARDING_CONFIG, false) ?: false
    }

    fun setIsOnBoardingConfig(): Boolean {
        return sharedPreferences?.edit()?.putBoolean(KEY_IS_ON_BOARDING_CONFIG, true)?.commit()
            ?: false
    }


}