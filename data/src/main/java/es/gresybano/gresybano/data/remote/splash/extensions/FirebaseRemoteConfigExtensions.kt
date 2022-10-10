package es.gresybano.gresybano.data.remote.splash.extensions

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import es.gresybano.gresybano.data.remote.splash.model.VersionControlDto
import org.json.JSONObject

private const val KEY_VERSION_CONTROL = "versionControl"

private const val KEY_MIN_VERSION = "minVersion"
private const val KEY_TITLE = "title"
private const val KEY_DESCRIPTION = "description"
private const val KEY_TEXT_BUTTON_ACCEPT = "textButtonAccept"
private const val KEY_TEXT_BUTTON_CANCEL = "textButtonCancel"

fun FirebaseRemoteConfig.getControlVersion() =
    try {
        with(JSONObject(getString(KEY_VERSION_CONTROL))) {
            VersionControlDto(
                getString(KEY_MIN_VERSION),
                getString(KEY_TITLE),
                getString(KEY_DESCRIPTION),
                getString(KEY_TEXT_BUTTON_ACCEPT),
                getString(KEY_TEXT_BUTTON_CANCEL)
            )
        }

    } catch (e: Exception) {
        null
    }