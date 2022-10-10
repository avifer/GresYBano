package es.gresybano.gresybano.data.repository

import es.gresybano.gresybano.data.preferences.splash.SplashPreferencesDataSource
import es.gresybano.gresybano.data.remote.splash.datasource.SplashRemoteDataSource
import es.gresybano.gresybano.data.remote.splash.model.toBo
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.domain.response.CodeExceptions
import es.gresybano.gresybano.domain.response.ExceptionInfo
import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.response.defaultResponseSuccessful
import es.gresybano.gresybano.domain.splash.repository.SplashRepository

class SplashRepositoryImpl(
    private val splashPreferencesDataSource: SplashPreferencesDataSource,
    private val splashRemoteDataSource: SplashRemoteDataSource,
) : SplashRepository, BaseRepository() {

    override suspend fun getIfOnBoardingConfig() =
        splashPreferencesDataSource.getIfOnBoardingConfig()

    override suspend fun setOnBoardingConfig() =
        splashPreferencesDataSource.setOnBoardingConfig()

    override suspend fun getVersionControl() =
        splashRemoteDataSource.getVersionControl().defaultResponseSuccessful {
            it?.let {
                Response.Successful(it.toBo())

            } ?: Response.Error(
                ExceptionInfo(
                    CodeExceptions.DATA_NULL_IN_SUCCESSFUL,
                    "Se encontro null en getVersionControl"
                )
            )
        }

}