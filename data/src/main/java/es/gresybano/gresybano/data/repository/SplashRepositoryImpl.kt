package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.preferences.splash.SplashPreferencesDataSource
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.splash.repository.SplashRepository

class SplashRepositoryImpl(
    private val splashPreferencesDataSource: SplashPreferencesDataSource,
) : SplashRepository, BaseRepository() {

    override suspend fun getIfOnBoardingConfig() =
        splashPreferencesDataSource.getIfOnBoardingConfig()

    override suspend fun setOnBoardingConfig() =
        splashPreferencesDataSource.setOnBoardingConfig()

}