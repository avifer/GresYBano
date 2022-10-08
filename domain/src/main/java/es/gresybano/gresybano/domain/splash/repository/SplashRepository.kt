package es.gresybano.gresybano.domain.splash.repository

import es.gresybano.gresybano.domain.response.Response

interface SplashRepository {

    suspend fun getIfOnBoardingConfig(): Response<Boolean>

    suspend fun setOnBoardingConfig(): Response<Boolean>

}