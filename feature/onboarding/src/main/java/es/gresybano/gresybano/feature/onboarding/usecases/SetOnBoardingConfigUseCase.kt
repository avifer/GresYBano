package es.gresybano.gresybano.feature.onboarding.usecases

import es.gresybano.gresybano.domain.response.*
import es.gresybano.gresybano.domain.splash.repository.SplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetOnBoardingConfigUseCase @Inject constructor(
    private val repositorySplash: SplashRepository
) {

    operator fun invoke(): Flow<Response<Boolean>> {
        return flow {
            emit(Response.Loading())
            with(repositorySplash.setOnBoardingConfig()) {
                emit(
                    if (isSuccessful() &&
                        getData() != null
                    ) {
                        this

                    } else {
                        Response.Error(getError() ?: ExceptionInfo(CodeExceptions.UNKNOWN))
                    }
                )
            }
        }
    }

}