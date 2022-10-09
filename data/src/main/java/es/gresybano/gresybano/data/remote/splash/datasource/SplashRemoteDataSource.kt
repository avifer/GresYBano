package es.gresybano.gresybano.data.remote.splash.datasource

import es.gresybano.gresybano.data.remote.splash.model.VersionControlDto
import es.gresybano.gresybano.domain.response.Response

interface SplashRemoteDataSource {

    suspend fun getVersionControl(): Response<VersionControlDto>

}