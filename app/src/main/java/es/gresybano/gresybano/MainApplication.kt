package es.gresybano.gresybano

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit.MINUTES

@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        private const val MINUTES_INTERVAL_FETCH = 1L
    }

    override fun onCreate() {
        super.onCreate()
        initFirebaseRemoteConfig()
    }

    private fun initFirebaseRemoteConfig() {
        with(Firebase.remoteConfig) {
            if (BuildConfig.DEBUG) {
                setConfigSettingsAsync(
                    remoteConfigSettings {
                        minimumFetchIntervalInSeconds = MINUTES.toSeconds(MINUTES_INTERVAL_FETCH)
                    }
                )
            }
        }
    }

}