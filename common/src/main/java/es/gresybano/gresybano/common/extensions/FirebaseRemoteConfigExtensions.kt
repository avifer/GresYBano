package es.gresybano.gresybano.common.extensions

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

private const val LAST_VERSION_APP = "last_version_app"
private const val KEY_MIN_VERSION_APP_DEFAULT = ""

fun FirebaseRemoteConfig.getDefaultValues(): HashMap<String, Any> {
    return hashMapOf(
        LAST_VERSION_APP to KEY_MIN_VERSION_APP_DEFAULT,
    )
}

fun FirebaseRemoteConfig.getLastVersionApp() = getString(LAST_VERSION_APP)

fun FirebaseRemoteConfig.isThereAnUpdateAvailable(versionName: String) =
    versionName < getLastVersionApp()