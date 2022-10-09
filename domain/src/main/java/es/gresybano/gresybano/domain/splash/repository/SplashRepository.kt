package es.gresybano.gresybano.domain.splash.repository

import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.splash.entity.VersionControlBo

interface SplashRepository {

    suspend fun getIfOnBoardingConfig(): Response<Boolean>

    suspend fun setOnBoardingConfig(): Response<Boolean>

    suspend fun getVersionControl(): Response<VersionControlBo>

}