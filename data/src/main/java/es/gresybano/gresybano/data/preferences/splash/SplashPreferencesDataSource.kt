package es.gresybano.gresybano.data.preferences.splash

import es.gresybano.gresybano.domain.response.Response

interface SplashPreferencesDataSource {

    suspend fun getIfOnBoardingConfig(): Response<Boolean>

    suspend fun setOnBoardingConfig(): Response<Boolean>

}