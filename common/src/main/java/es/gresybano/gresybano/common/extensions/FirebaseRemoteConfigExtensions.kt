package es.gresybano.gresybano.common.extensions

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

private const val KEY_MIN_VERSION_APP = "min_version_app"
private const val KEY_MIN_VERSION_APP_DEFAULT = ""

fun FirebaseRemoteConfig.getDefaultValues(): HashMap<String, Any> {
    return hashMapOf(
        KEY_MIN_VERSION_APP to KEY_MIN_VERSION_APP_DEFAULT,
    )
}

fun FirebaseRemoteConfig.getMinVersion() = getString(KEY_MIN_VERSION_APP)

fun FirebaseRemoteConfig.needForceUpdate(versionName: String) = versionName < getMinVersion()