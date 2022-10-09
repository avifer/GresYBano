package es.gresybano.gresybano.feature.splash.usecases

import es.gresybano.gresybano.domain.response.Response
import es.gresybano.gresybano.domain.splash.entity.VersionControlBo
import es.gresybano.gresybano.domain.splash.repository.SplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMinVersionUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {

    operator fun invoke(): Flow<Response<VersionControlBo>> {
        return flow {
            emit(Response.Loading())
            emit(splashRepository.getVersionControl())
        }
    }

}