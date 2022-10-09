package es.gresybano.gresybano.data.remote.splash.datasource

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import es.gresybano.gresybano.data.remote.splash.extensions.getControlVersion
import es.gresybano.gresybano.data.utils.BaseRepository
import es.gresybano.gresybano.data.utils.CustomException
import es.gresybano.gresybano.data.utils.safeRemoteCall
import es.gresybano.gresybano.domain.response.CodeExceptions
import kotlinx.coroutines.tasks.await

class SplashRemoteDataSourceImpl(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : SplashRemoteDataSource, BaseRepository() {

    override suspend fun getVersionControl() =
        safeRemoteCall {
            with(firebaseRemoteConfig) {
                fetchAndActivate().await()
                getControlVersion() ?: kotlin.run {
                    throw CustomException(
                        CodeExceptions.NOT_FIND_DATA_VERSION_CONTROL,
                        "Faltan campos del objeto version control"
                    )
                }
            }
        }

}