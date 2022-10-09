package es.gresybano.gresybano.data.remote.splash.model

data class VersionControlDto(
    val minVersion: String,
    val title: String,
    val description: String,
    val textButtonAccept: String,
    val textButtonCancel: String,
)