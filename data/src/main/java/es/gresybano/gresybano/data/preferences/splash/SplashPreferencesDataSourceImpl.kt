package es.gresybano.gresybano.data.preferences.splash

import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.data.preferences.PreferencesManager
import es.gresybano.gresybano.data.utils.safeLocalCall
import es.gresybano.gresybano.domain.response.Response

class SplashPreferencesDataSourceImpl(
    private val preferencesManager: PreferencesManager
) : SplashPreferencesDataSource {

    companion object {
        private const val KEY_IS_ON_BOARDING_CONFIG = "KEY_IS_ON_BOARDING_CONFIG"
    }

    override suspend fun getIfOnBoardingConfig(): Response<Boolean> {
        return safeLocalCall {
            preferencesManager.getValue(KEY_IS_ON_BOARDING_CONFIG, EMPTY_STRING).toBoolean()
        }
    }

    override suspend fun setOnBoardingConfig(): Response<Boolean> {
        return safeLocalCall {
            preferencesManager.setValue(KEY_IS_ON_BOARDING_CONFIG, true)
        }
    }

}